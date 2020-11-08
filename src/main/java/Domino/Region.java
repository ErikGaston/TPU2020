package Domino;

import Soporte.TSB_OAHashtable;

import java.util.Collection;

public class Region {
    private String codigo;
    private String nombre;
    private TSB_OAHashtable subregiones;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getCodigo() {
        return codigo;
    }

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.subregiones = new TSB_OAHashtable();
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
        Region sub = (Region) subregiones.get(substring);
        if (sub == null)
            subregiones.put(substring, new Region(substring, " " ));

        return (Region) subregiones.get(substring);
    }
}
