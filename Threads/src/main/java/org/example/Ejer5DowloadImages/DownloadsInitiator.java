package org.example.Ejer5DowloadImages;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadsInitiator {
    private static final Path urlFile=Path.of("src/main/java/org/example/Ejer5DowloadImages/animal-urls.txt");

    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool(10);

        if (!Files.exists(urlFile)){
            System.out.println("El archivo de urls no existe: "+ urlFile.getFileName().toString());
            System.exit(1);
        }

        

    }
}
