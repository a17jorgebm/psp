package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedirectOuputAndDestroyForcibly {
    private static final int WAIT_TIME=500;
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder processBuilder=new ProcessBuilder();

        Path directory= Path.of("src/main/java/org/example/ficheirosTracert");
        Files.createDirectories(directory);

        Path logFile=directory.resolve("logFile.txt");

        processBuilder.command(List.of("cmd","/c","tracert","google.es"));
        processBuilder.redirectOutput(logFile.toFile());
        Process process=processBuilder.start();

        if (!process.waitFor(WAIT_TIME,TimeUnit.MILLISECONDS)){
            process.getOutputStream().close();
            process.destroyForcibly();
            throw new InterruptedException("Interrumpiuse o proceso despois de "+WAIT_TIME);
        }

        if (process.isAlive()){
            System.out.println("ta vivo");
        }
    }
}
