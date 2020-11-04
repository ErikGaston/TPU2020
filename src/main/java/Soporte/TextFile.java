package Soporte;

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
}
