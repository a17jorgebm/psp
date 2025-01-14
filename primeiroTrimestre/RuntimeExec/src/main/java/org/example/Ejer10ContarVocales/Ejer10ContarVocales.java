package org.example.Ejer10ContarVocales;

import javax.swing.table.TableRowSorter;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejer10ContarVocales {
    public static void main(String[] args) throws IOException, InterruptedException {
        char[] letras={'a','e','i','o','u'};

        Path ficheiro= Paths.get("ficheiro.txt");
        if (!Files.exists(ficheiro)){
            System.err.println("Non exise o ficheiro "+ficheiro.getFileName());
            System.exit(2);
        }

        Path ficheiroSaida=Paths.get("ficheiroSaida.txt");
        Files.deleteIfExists(ficheiroSaida);

        ProcessBuilder builder=new ProcessBuilder();
        for (char letra:letras){
            builder.command("java","-cp",".\\target\\classes\\","org.example.Ejer10ContarVocales.ContarVocales","./"+ficheiro.getFileName(),String.valueOf(letra));
            Process proceso=builder.start();
            proceso.waitFor();
        }

        if (!Files.exists(ficheiroSaida)){
            System.out.println("Erro, non existe o ficheiro");
            System.exit(1);
        }
        try(BufferedReader output=new BufferedReader(new FileReader(ficheiroSaida.toFile()))){
            String linea;
            while ((linea=output.readLine())!=null){
                System.out.println(linea);
            }
        }
    }
}
