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

    @FXML
    public void initialize() {
        cboDistrito.setDisable(true);
        cboSeccion.setDisable(true);
        cboCircuito.setDisable(true);
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
        cboSeccion.setDisable(false);
        cboCircuito.setDisable(false);

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

        if(cboSeccion.getValue() != null)
        {
            Region seccion = (Region) cboSeccion.getValue();
            ol = FXCollections.observableArrayList(seccion.getSubregiones());
            cboCircuito.setItems(ol);
            ol = FXCollections.observableArrayList(resultados.getResultadosRegion(seccion.getCodigo()));
            lvwResultados.setItems(ol);
        }
        else
            cboCircuito.setItems(null);
    }

    public void elegirCircuito(ActionEvent actionEvent) {
        ObservableList ol;

        if(cboCircuito.getValue() != null)
        {
            Region circuito = (Region) cboCircuito.getValue();
//            ol = FXCollections.observableArrayList(circuito.getSubregiones());
//            cboCircuito.setItems(ol);
            ol = FXCollections.observableArrayList(resultados.getResultadosRegion(circuito.getCodigo()));
            lvwResultados.setItems(ol);
        }
    }

    public void elegirMesa(ActionEvent actionEvent) {
        //Implemetar busqueda subregiones mesa y mosrtarlas.
    }
}
