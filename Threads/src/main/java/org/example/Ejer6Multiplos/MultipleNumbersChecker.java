package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultipleNumbersChecker {
    private static final int NUMBER_OF_NUMBERS_TO_GENERATE=50;
    public static void main(String[] args) {
        //generacion da lista de numeros
        LinkedList<BigInteger> numberList=new LinkedList<>();
        ExecutorService threadExecutor= Executors.newSingleThreadExecutor();
        for (int i=0;i<NUMBER_OF_NUMBERS_TO_GENERATE;i++){
            threadExecutor.execute(new NumberGenerator(numberList));
        }
        threadExecutor.shutdown();
        try{
            if (threadExecutor.awaitTermination(60, TimeUnit.SECONDS)){
                threadExecutor.shutdownNow(); //forzamos
            }
        }catch (InterruptedException e){
            System.out.println("Error ao generar os numeros: "+e.getMessage());
            System.exit(1);
        }


    }
}
