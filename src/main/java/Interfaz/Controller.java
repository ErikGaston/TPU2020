package Interfaz;
import Domino.Agrupaciones;
import Soporte.TextFile;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Controller {
    public Label lblOrigen;
    public TextArea textAgrupaciones;
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

        Agrupaciones agrupaciones = new Agrupaciones(lblOrigen.getText());
        textAgrupaciones.setText(agrupaciones.toString());

        TextFile archRegiones = new TextFile(lblOrigen.getText() + "\\descripcion_regiones.dsv");

        TextFile archMesas = new TextFile(lblOrigen.getText() + "\\mesas_totales_agrp_politica.dsv");



    }
}
