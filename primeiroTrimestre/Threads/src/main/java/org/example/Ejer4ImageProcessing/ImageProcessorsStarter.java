package org.example.Ejer4ImageProcessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ImageProcessorsStarter {
    private static final Path IMAGE_LOCATION_DIRECTORY=Path.of("src/main/java/org/example/Ejer4ImageProcessing/resources/original");
    private static final int NUMBER_OF_THREADS=5;


    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        try{
            List<Path> filesOfDirectory = Files.list(IMAGE_LOCATION_DIRECTORY)
                    .filter(Files::isRegularFile)
                    .map(Path::toAbsolutePath)
                    .toList();
            for (Path file : filesOfDirectory){
                Runnable thread=new ImageProcessor(file);
                threadPool.execute(thread);
            }
            threadPool.shutdown();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
