package org.example.Ejer9GuessNumber.withSyncronized;

/*
DU2 - Exercise 9 - Java Threads - Synchronization - Hidden number

We want to create a multithreading application.

The main thread must generate a random number (use java.util.Random) between 0 and 100.
That main thread must create ten threads whose job is to guess the number generated by the main thread.

To perform their tasks all threads must share an object of the HiddenNumber class.
The HiddenNumber class must have a method int numberGuess(int num) that has to return the following values:

    -1 if the game is over because one thread has guessed the number
    1 if the proposed number (num) is the hidden number
    0 otherwise

Follow the given specifications to create the application.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GuessNumberExecutor {
    private static final int NUM_THREADS=10;
    public static void main(String[] args) {
        HiddenNumber hiddenNumber=new HiddenNumber();
        ExecutorService poolThreads= Executors.newFixedThreadPool(NUM_THREADS);

        for (int i=0;i<NUM_THREADS;i++){
            poolThreads.execute(new GuessNumber(hiddenNumber));
        }

        poolThreads.shutdown();
        try{
            if (poolThreads.awaitTermination(60, TimeUnit.SECONDS)){
                poolThreads.shutdownNow();
            }
        }catch (InterruptedException e){
            System.out.println("Error nos threads, houbo que interrumpilos");
        }
    }
}
