package org.example.poolDeThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int numeroThreads=Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        String[] nombres = {"Ana", "Carlos", "Beatriz", "Luis", "Diana", "Juan", "Marta", "Pedro", "Sofía", "Gabriel"};

        ExecutorService poolThreads= Executors.newFixedThreadPool(numeroThreads);

        for (int i=0;i< nombres.length;i++){
            Runnable rg=new RunnableGreet(nombres[i]);
            poolThreads.execute(rg);
        }
        poolThreads.shutdown();
    }
}
