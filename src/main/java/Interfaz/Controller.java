package Interfaz;
import Domino.Agrupaciones;
import Domino.Regiones;
import Soporte.TextFile;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Controller {
    public Label lblOrigen;
    public ListView lvwResultados;
    public ComboBox cboDistricto;
    public ComboBox cboSeccion;
    public ComboBox cboCircuito;

    File archivo;
    public void cambiarDestino(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione ubicacion de los datos.");
        dc.setInitialDirectory(new File(lblOrigen.getText()));
        archivo = dc.showDialog(null);
        if (archivo != null)
            lblOrigen.setText(archivo.getPath());

    }

    public void cargarDatos(ActionEvent actionEvent) {

        //Carga de Grilla
        ObservableList ol;
        //Genracion de lista agrupaciones
        Agrupaciones agrupaciones = new Agrupaciones(lblOrigen.getText());
        ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        lvwResultados.setItems(ol);
        //Generacion de lista de districtos
        Regiones regiones = new Regiones(lblOrigen.getText());
        ol = FXCollections.observableArrayList(regiones.getDistrictos());
        cboDistricto.setItems(ol);

        Resultados resultados = new Resultados(lblOrigen.getText());
    }
    public void filtrarSecciones(ActionEvent actionEvent){
        ObservableList ol;
        Region distrito = (Region) cboDistrito.getValue();
        ol = FXCollections.observableArrayList(distrito.getSubRegiones());
        cboSeccion.setItems(ol);
    }

    public void filtrarCircuitos(ActionEvent actionEvent){
        ObservableList ol;
        if(cboSeccion.getValue() != null){
            Region seccion = (Region) cboSeccion.getValue();
            ol = FXColleccions.observableArrayList(seccion.getSubRegiones());
            cboCircuito.setItems(ol);
        }else
            cboCircuito.setItems(null);
    }
}
