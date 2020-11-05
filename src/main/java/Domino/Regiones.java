package Domino;

import Soporte.TextFile;

public class Regiones {
    private TextFile fileRegiones;
    private Region pais;

    public Regiones(String path) {
        fileRegiones = new TextFile(path + "\\descripcion_regiones.dsv");
        pais = fileRegiones.identificarRegiones();
    }
}
