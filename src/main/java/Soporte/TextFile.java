package Soporte;

import Domino.Agrupacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile {
    private File file;

    public TextFile(String path) {
        file = new File(path);
    }

    public String leerEncabezado(){
        String line = "";

        try {
            Scanner scnr = new Scanner(file);
            if (scnr.hasNext()){
                line = scnr.nextLine();
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("No se puedo encontrar el archivo");
        }
        return line;
    }

    public TSBHashtable identificarAgrupaciones(){
        String line = "", campos[];
        TSBHashtable htable = new TSBHashtable();
        Agrupacion agrupacion;
        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNext()){
                line = scnr.nextLine();
                campos = line.split("\\|");
                if (campos[0].compareTo("000100000000000")==0)
                {
                    agrupacion = new Agrupacion(campos[2], campos[3]);
                    htable.put(agrupacion.getCodigo(), agrupacion);
                }
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo!");;
        }
        return htable;
    }
}
