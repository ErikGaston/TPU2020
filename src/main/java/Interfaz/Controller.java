package Interfaz;
import Domino.*;
import Soporte.TextFile;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import java.io.File;

public class Controller {

    public Resultados resultados;
    public Label lblOrigen;
    public ListView lvwResultados;
    public ComboBox cboDistrito;
    public ComboBox cboSeccion;
    public ComboBox cboCircuito;
    public Button btnCargarDatos;
    public ComboBox cboMesa;

    @FXML
    public void initialize() {
        cboDistrito.setDisable(true);
        cboSeccion.setDisable(true);
        cboCircuito.setDisable(true);
        cboMesa.setDisable(true);
        btnCargarDatos.setDisable(true);

    }

    File archivo;
    public void cambiarDestino(ActionEvent actionEvent) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccione ubicacion de los datos.");
        dc.setInitialDirectory(new File(lblOrigen.getText()));
        archivo = dc.showDialog(null);
        if (archivo != null)
            lblOrigen.setText(archivo.getPath());

        btnCargarDatos.setDisable(false);
    }

    public void cargarDatos(ActionEvent actionEvent) {

        ObservableList ol;
        Agrupaciones.leerAgrupaciones(lblOrigen.getText());
        Regiones regiones = new Regiones(lblOrigen.getText());
        ol = FXCollections.observableArrayList(regiones.getDistritos());
        cboDistrito.setItems(ol);
        resultados = new Resultados(lblOrigen.getText());
        ol = FXCollections.observableArrayList(resultados.getResultadosRegion("00"));
        lvwResultados.setItems(ol);

        cboDistrito.setDisable(false);
    }

    public void elegirDistrito(ActionEvent actionEvent){
        ObservableList ol;
        Region distrito = (Region) cboDistrito.getValue();
        ol = FXCollections.observableArrayList(distrito.getSubregiones());
        cboSeccion.setItems(ol);
        ol = FXCollections.observableArrayList(resultados.getResultadosRegion(distrito.getCodigo()));
        lvwResultados.setItems(ol);

        cboSeccion.setDisable(false);
        //if (cboDistrito.getItems()==null) cboMesa.setItems(null);
    }

    public void elegirSeccion(ActionEvent actionEvent){
        ObservableList ol;

        if(cboSeccion.getValue() != null)
        {
            try {
                Region seccion = (Region) cboSeccion.getValue();
                ol = FXCollections.observableArrayList(seccion.getSubregiones());
                cboCircuito.setItems(ol);
                ol = FXCollections.observableArrayList(resultados.getResultadosRegion(seccion.getCodigo()));
                lvwResultados.setItems(ol);
                cboCircuito.setDisable(false);
            }
            catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No hay resultados para mostrar!");
                alert.showAndWait();
                cboSeccion.setItems(null);
            }
        }
        else
            cboCircuito.setItems(null);
            cboMesa.setItems(null);
    }

    public void elegirCircuito(ActionEvent actionEvent) {
        ObservableList ol;

        if(cboCircuito.getValue() != null)
        {
            try {
                Region circuito = (Region) cboCircuito.getValue();
                ol = FXCollections.observableArrayList(circuito.getSubregiones());
                cboMesa.setItems(ol);
                ol = FXCollections.observableArrayList(resultados.getResultadosRegion(circuito.getCodigo()));
                lvwResultados.setItems(ol);
                cboMesa.setDisable(false);
            }
            catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No hay resultados para mostrar!");
                alert.showAndWait();
                cboCircuito.setItems(null);
            }
        }
        else
            cboCircuito.setItems(null);
            //cboMesa.setItems(null);

    }

    public void elegirMesa(ActionEvent actionEvent) {
        ObservableList ol;

        if(cboMesa.getValue() != null)
        {
            Region mesa = (Region) cboMesa.getValue();
            try {
                ol = FXCollections.observableArrayList(resultados.getResultadosRegion(mesa.getCodigo()));
                lvwResultados.setItems(ol);
            }
            catch (NullPointerException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("No hay resultados para mostrar!");
                alert.showAndWait();
                cboMesa.setItems(null);
            }
        }
        else
            cboMesa.setItems(null);
    }
}
