package Domino;

import Soporte.TSBHashtable;

import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private TSBHashtable subregiones;

    public String getCodigo() {
        return codigo;
    }

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.subregiones = new TSBHashtable();
    }

    public void agregarSubregion(Region region) {
        subregiones.put(region.codigo, region);
    }

    public Collection getSubregiones() {
        return subregiones.values();
    }

    public Region getSubregion(String codigo){
        return (Region) subregiones.get(codigo);
    }

    @Override
    public String toString() {
        return "(" + codigo + ") " + nombre;
    }

    public Region getOrPutSubregion(String substring) {
        Region sub = (Region) subregiones.get(codigo);
        if (sub == null)
            subregiones.put(codigo, new Region(codigo, " " ));

        return (Region) subregiones.get(codigo);
    }
}
