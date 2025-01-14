package org.example.Ejer10ContarVocales;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContarVocales {
    public static void main(String[] args) {
        if (args.length<2){
            System.err.println("Argumentos inválidos, uso: ContarVocales nomeFicheiro letra");
            System.exit(1);
        }

        Path file= Paths.get(args[0]);
        if (!Files.exists(file)){
            System.err.println("O ficheiro "+ file.getFileName() +" non existe");
            System.exit(2);
        }

        String letra=args[1];
        int contador=0;

        Pattern patron= Pattern.compile(letra,Pattern.CASE_INSENSITIVE);

        try(BufferedReader input=new BufferedReader(new FileReader(file.toFile()))){
            String linea;
            while ((linea=input.readLine())!=null){
                Matcher matcher=patron.matcher(linea);
                while (matcher.find()){
                    contador++;
                }
            }
        }catch (IOException e){
            System.err.println("Error ao ler o ficheiro: "+e.getMessage());
            System.exit(2);
        }

        try(BufferedWriter output=new BufferedWriter(new FileWriter("ficheiroSaida.txt",true))){
            output.write("Letra a: "+contador+"\n");
        }catch (IOException e){
            System.err.println("Error ao escribir o ficheiro: "+e.getMessage());
            System.exit(3);
        }
    }
}
