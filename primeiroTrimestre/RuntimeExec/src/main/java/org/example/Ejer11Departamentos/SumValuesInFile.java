package org.example.Ejer11Departamentos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class SumValuesInFile {
    public static void main(String[] args) {
        if (args.length < 1){
            System.err.println("Se requiere el nombre del fichero como parámetro");
            System.exit(1);
        }
        Path ficheiro= Paths.get(args[0]);
        if (!Files.exists(ficheiro)){
            System.err.println("Non existe o ficheiro "+ficheiro.getFileName());
            System.exit(2);
        }

        Double contador=0d;
        try(BufferedReader reader=new BufferedReader(new FileReader(ficheiro.toFile()))){
            String line;
            while ((line=reader.readLine())!=null){
                try {
                    Double numero= Double.valueOf(line);
                    contador+=numero;
                }catch (NumberFormatException e){}
            }
        }catch (IOException e){
            System.err.println("Erro ao ler o ficheiro "+ficheiro.getFileName());
            System.exit(3);
        }
        System.out.printf(Locale.US,"%.2f",contador);
    }
}
