package Domino;

import Soporte.TextFile;

import java.util.Collection;

public class Regiones {
    private TextFile fileRegiones;
    private Region pais;
    private TextFile fileMesas;

    public Regiones(String path) {
        fileRegiones = new TextFile(path + "\\descripcion_regiones.dsv");
        pais = fileRegiones.identificarRegiones();

        fileMesas = new TextFile(path + "\\mesas_totales_lista.dsv");
        fileMesas.agragarMesas(pais);
    }

    public Collection getDistritos(){
        return pais.getSubregiones();
    }
}
