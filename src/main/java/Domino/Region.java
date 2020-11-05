package Domino;

import Soporte.TSBHashtable;

public class Region {
    private String codigo;
    private String nombre;
    private TSBHashtable subregiones;

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.subregiones = new TSBHashtable();
    }

    public void agregarSubregion(Region region) {
        subregiones.put(region.codigo, region);
    }
}
