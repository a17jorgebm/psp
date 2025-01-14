package org.example.Ejer5DowloadImages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
DU2 - Exercise 5 - Java Threads - Thread pool - Download files

We want to create a multithreaded application.

The main thread must create a thread pool of size ten. Use the file animal-urls.txt to get the URL from which download files.

Each thread must download a file from one of the URLs in animal-urls.txt.

Place the processed images in a folder called results. The names of the processed files should be animali.jpg where i is the
number of the line in animal-urls.txt where the URL of the file is placed.

Once all files have been processed, the program should terminate.
 */

public class DownloadsInitiator {
    private static final Path urlFile=Path.of("src/main/java/org/example/Ejer5DowloadImages/animal-urls.txt");
    private static final int POOL_SIZE=10;

    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool(POOL_SIZE);

        long tempoInicio=System.currentTimeMillis();
        System.out.println("Descargando...");

        if (!Files.exists(urlFile)){
            System.out.println("El archivo de urls no existe: "+ urlFile.getFileName().toString());
            System.exit(1);
        }

        try(BufferedReader bf=new BufferedReader(new FileReader(urlFile.toFile()))){
            String url;
            int i=1;
            while ((url=bf.readLine())!=null){
                Runnable thread=new ImageDownloader(url,i++);
                threadPool.execute(thread);
            }
        }catch (IOException e){
            System.out.println("Error: "+e.getMessage());
            System.exit(2);
        }

        threadPool.shutdown(); //esto fai que non se acepten mais tareas, pero que as actuales podan terminar

        //para que o programa espere a que acaben todos os hilos antes de seguir co programa pricipal habería que facer esto
        /*
            threadPool.shutdown(); // No más tareas se aceptarán, pero las actuales terminan
            try {
                // Espera a que todos los hilos terminen hasta un máximo de 1 hora
                if (!threadPool.awaitTermination(1, java.util.concurrent.TimeUnit.HOURS)) {
                    System.out.println("Algunos hilos no terminaron antes del tiempo máximo.");
                }
            } catch (InterruptedException e) {
                System.out.println("La espera de terminación fue interrumpida: " + e.getMessage()); //se pasa mais de 1 hora lanza error
                System.exit(4);
            }
         */

        System.out.println("Tempo transcurrido: "+(System.currentTimeMillis()-tempoInicio));
    }
}
