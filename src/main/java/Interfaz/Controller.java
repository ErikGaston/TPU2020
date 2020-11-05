package Interfaz;
import Domino.Agrupaciones;
import Soporte.TextFile;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Controller {
    public Label lblOrigen;
    public ListView lvwResultados;

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

        Agrupaciones agrupaciones = new Agrupaciones(lblOrigen.getText());
        ObservableList ol = FXCollections.observableArrayList(agrupaciones.getResultados());
        lvwResultados.setItems(ol);


    }
}
