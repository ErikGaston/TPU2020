package Domino;


import Soporte.TSBHashtable;
import  Soporte.TextFile;

public class Agrupaciones {
    private TextFile fileAgrupaciones;
    private TextFile fileMesas;
    private TSBHashtable table;

    public Agrupaciones(String path) {
        fileAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        table = fileAgrupaciones.identificarAgrupaciones();
        fileMesas.contarVotosAgrp();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object objeto: table.values()) {
            sb.append("\n" + objeto);
        }
        return sb.toString();
    }
}