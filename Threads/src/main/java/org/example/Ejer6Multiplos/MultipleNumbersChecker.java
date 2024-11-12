package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultipleNumbersChecker {
    private static final int NUMBER_OF_NUMBERS_TO_GENERATE=50;
    private static final int NUMBER_OF_POOL_THREADS=12;

    public static void main(String[] args) {
        System.out.println("Generando numeros...");

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

        System.out.println("Comprobando multiplos...");

        //comprobación de mutiplos
        ExecutorService threadPool=Executors.newFixedThreadPool(NUMBER_OF_POOL_THREADS);

        Map<BigInteger, Map<Integer,Boolean>> concurrentHashOfNumbersAndMultiplesValues=new ConcurrentHashMap<>();
        numberList.forEach((n)->{
            Map<Integer,Boolean> mapaInterior=new ConcurrentHashMap<>();
            mapaInterior.put(3,false);
            mapaInterior.put(5,false);
            mapaInterior.put(11,false);
            concurrentHashOfNumbersAndMultiplesValues.put(n, mapaInterior);

            threadPool.execute(new CheckMultipleOf3Thread(n,concurrentHashOfNumbersAndMultiplesValues));
            threadPool.execute(new CheckMultipleOf5Thread(n,concurrentHashOfNumbersAndMultiplesValues));
            threadPool.execute(new CheckMultipleOf11Thread(n,concurrentHashOfNumbersAndMultiplesValues));
        });

        try{
            if (!threadExecutor.awaitTermination(60,TimeUnit.SECONDS)){
                threadExecutor.shutdownNow();
            }
        }catch (InterruptedException e){
            System.out.println("An error ocurred procesing the numbers: "+e.getMessage());
        }

        for (BigInteger num: concurrentHashOfNumbersAndMultiplesValues.keySet()){
            System.out.print(num);
            for (Integer value: concurrentHashOfNumbersAndMultiplesValues.get(num).keySet()){
                System.out.printf("\n\t%d: %s",
                        value,
                        concurrentHashOfNumbersAndMultiplesValues.get(num).get(value));
            }
            System.out.println("\n");
        }

        System.out.println("Terminado");

    }
}
