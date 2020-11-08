package Domino;

import Soporte.TSBHashtable;
import  Soporte.TextFile;

import java.util.Collection;

public class Agrupaciones {
    private static TSBHashtable inicial;
    private TSBHashtable conteo;


    public Agrupaciones() {
        conteo = new TSBHashtable();
        for (Object o: inicial.values())
        {
            Agrupacion a = (Agrupacion) o;
            conteo.put(a.getCodigo(), new Agrupacion(a.getCodigo(),a.getNombre()));
        }
    }

    public static void leerAgrupaciones(String path)
    {
        TextFile fileAgrupaciones = new TextFile( path + "\\descripcion_postulaciones.dsv");
        inicial = fileAgrupaciones.identificarAgrupaciones();
    }

    public Collection getResultados(){
        return conteo.values();
    }

    public Agrupacion getAgrupacion(String codAgrupacion){
        return (Agrupacion) conteo.get(codAgrupacion);
    }
}