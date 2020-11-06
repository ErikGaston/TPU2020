package Interfaz;
import Domino.*;
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

    public Resultados resultados;
    public Label lblOrigen;
    public ListView lvwResultados;
    public ComboBox cboDistrito;
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
        Agrupaciones.leerAgrupaciones(lblOrigen.getText());
        Regiones regiones = new Regiones(lblOrigen.getText());
        ol = FXCollections.observableArrayList(regiones.getDistritos());
        cboDistrito.setItems(ol);
        resultados = new Resultados(lblOrigen.getText());
        ol = FXCollections.observableArrayList(resultados.getResultadosRegion("00"));
        lvwResultados.setItems(ol);
    }

    public void filtrarSecciones(ActionEvent actionEvent){
        ObservableList ol;
        Region distrito = (Region) cboDistrito.getValue();
        ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSeccion.setItems(ol);

        ol = FXCollections.observableArrayList(resultados.getResultadosRegion(distrito.getCodigo()));
        lvwResultados.setItems(ol);
    }

    public void filtrarCircuitos(ActionEvent actionEvent){
        ObservableList ol;

        if(cboSeccion.getValue() != null){
            Region seccion = (Region) cboSeccion.getValue();
            ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cboCircuito.setItems(ol);
            ol = FXCollections.observableArrayList(resultados.getResultadosRegion(seccion.getCodigo()));
            lvwResultados.setItems(ol);

        }else
            cboCircuito.setItems(null);
    }
}
