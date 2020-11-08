package Domino;

import Soporte.TSB_OAHashtable;
import Soporte.TextFile;
import javafx.scene.text.Text;

import java.util.Collection;

public class Resultados {
    private TSB_OAHashtable tabla;

    public Resultados(String path){
        tabla = new TSB_OAHashtable();
        TextFile fileMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        fileMesas.sumarVotosPorRegion(this);
    }

    public void sumarVotos(String codRegion, String codAgrupacion, int votos){
        if (tabla.get(codRegion) == null)
        {
            tabla.put(codRegion, new Agrupaciones());
        }

        Agrupaciones a = (Agrupaciones) tabla.get(codRegion);
        a.getAgrupacion(codAgrupacion).sumarVotos(votos);
    }

    public Collection getResultadosRegion(String codRegion){
        Agrupaciones a = (Agrupaciones ) tabla.get(codRegion);
        return a.getResultados();
    }
}
