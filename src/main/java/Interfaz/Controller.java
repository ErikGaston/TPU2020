package Interfaz;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Controller {
    public Label lblOrigen;
    File archivo;
    public void cambiarDestino(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione ubicacion de los datos.");
        dc.setInitialDirectory(new File(lblOrigen.getText()));
        archivo = dc.showDialog(null);
        if (archivo != null)
            lblOrigen.setText(archivo.getPath());

    }
}
