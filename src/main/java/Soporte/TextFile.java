package Soporte;

import Domino.Agrupacion;
import Domino.Region;
import Domino.Resultados;

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

    public TSBHashtable contarVotosAgrp(TSBHashtable htable) {
        String line = "", campos[];

        Agrupacion agrupacion;
        int votos;
        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNext()){
                line = scnr.nextLine();
                campos = line.split("\\|");
                if (campos[4].compareTo("000100000000000")==0)
                {
                    agrupacion = (Agrupacion) htable.get(campos[5]);
                    votos = Integer.parseInt(campos[6]);
                    agrupacion.sumarVotos(votos);
                }
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo!");;
        }
        return htable;
    }

    public Region identificarRegiones(){
        String line = "", campos[];
        Region pais = new Region("00", "Argentina");
        Region distrito, seccion;

        try {
            Scanner scnr = new Scanner(file);
            while (scnr.hasNext()){
                line = scnr.nextLine();
                campos = line.split("\\|");
                String codigo = campos[0];
                String nombre = campos[1];

                switch (codigo.length())
                {
                    case 2:
                        //provincia
                        distrito = pais.getOrPutSubregion(codigo);
                        distrito.setNombre(nombre);
                        break;

                    case 5:
                        // nivel de localidad
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo);
                        seccion.setNombre(nombre);
                        break;
                    case 11:

                        // Nivel de circuito.
                        distrito = pais.getOrPutSubregion(codigo.substring(0, 2));
                        seccion = distrito.getOrPutSubregion(codigo.substring(0, 5));
                        seccion.agregarSubregion(new Region(codigo, nombre));
                        break;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo!");;
        }
        return pais;
    }


    public void sumarVotosPorRegion(Resultados resultados) {
        String linea = "", campos[], codAgrupacion;

        int votos;
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                linea = sc.nextLine();
                campos = linea.split("\\|");
                codAgrupacion = campos[5];

                if (campos[4].compareTo("000100000000000") == 0)
                {
                    votos = Integer.parseInt(campos[6]);
                    resultados.sumarVotos("00", codAgrupacion, votos);
                    for (int i = 0; i < 3; i++)
                    {
                        resultados.sumarVotos(campos[i], codAgrupacion, votos);
                    }
                }
            }

        } catch (FileNotFoundException exception)
        {
            System.err.println("No se encontró el archivo!");
        }
    }
}
