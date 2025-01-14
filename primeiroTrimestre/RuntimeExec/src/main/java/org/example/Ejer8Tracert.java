package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Ejer8Tracert {
    public static void main(String[] args) throws IOException,InterruptedException {
        int tempoEspera=500;
        Path directorioSaida= Paths.get("logs_saida");
        Files.createDirectories(directorioSaida);

        Path ficheiroSaida=directorioSaida.resolve("outputTracert.txt");
        if (Files.notExists(ficheiroSaida)){
            Files.createFile(ficheiroSaida);
        }

        ProcessBuilder pBuilder=new ProcessBuilder();
        pBuilder.command("cmd","/c","tracert","iessanclemente.net");
        //esto ainda que se pare pode escribir igualmente no arquivo,
        // xa que creo que usa un buffer interno, e os buffers parece ser que
        // son independientes do programa
        pBuilder.redirectOutput(ficheiroSaida.toFile());

        Process proceso=pBuilder.start();
        if(!proceso.waitFor(tempoEspera, TimeUnit.MILLISECONDS)){
            proceso.destroyForcibly();
            throw new InterruptedException("Interrumpiuse o proceso despois de "+tempoEspera+"ms");
        }
        if (proceso.isAlive()){
            System.out.println("ta vivo");
        }
    }
}
