package org.example.Ejer10ContarVocales;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContarVocales {
    public static void main(String[] args) {
        if (args.length<2){
            System.err.println("Arumentos inválidos, uso: ContarVocales nomeFicheiro letra");
            System.exit(1);
        }

        Path file= Paths.get(args[0]);
        if (Files.exists(file)){
            System.err.println("O ficheiro "+ file.getFileName() +" non existe");
            System.exit(2);
        }

        String letra=args[1];
        int contador=0;

        Matcher matcher= Pattern.compile("a",Pattern.CASE_INSENSITIVE).matcher(letra);
        while (matcher.find()){
            contador++;
        }

        System.out.print(contador);
    }
}
