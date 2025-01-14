package org.example.ejer2Temperaturas;

/*
DU2 - Exercise 2 - Java Threads - Collaboration

This activity aims to enter the world of multi-threaded programming by creating different threads to cooperate with each other.

We have a list of all the maximum temperatures that have occurred every day in Compostela in the last 10 years. We have them stored in an array of integers.

We want to calculate the average temperature of the last 10 years.
To do this first we will simulate temperatures by filling an array of 3650 positions randomly
with integer numbers, taking values between -30 and 50.

To find the average temperature, we will divide the array into 10 parts.
Each thread will have to search within the assigned array chunk for the average temperature.

Finally, once the average of each subset of the array is found, the program will tell us which is the average of the averages returned by each thread.
 */

import java.util.ArrayList;
import java.util.Random;

public class FindAverageOfTemperatures {

    private static final int NUMERO_TEMPERATURAS=3650;
    private static final int TEMPERATURA_MINIMA=-30;
    private static final int TEMPERATURA_MAXIMA=50;
    private static final int TAMANHO_SUBARRAYS=350;


    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> temperaturas=generarArrayTemperaturas(NUMERO_TEMPERATURAS);
        ArrayList<ArrayList<Integer>> listasTemperaturas=dividirArrayList(temperaturas,TAMANHO_SUBARRAYS);
        ArrayList<Integer> listaSumaTemperaturas=new ArrayList<>();
        ArrayList<Thread> threads=new ArrayList<>();

        for (ArrayList<Integer> subArray: listasTemperaturas){
            SumTemperatureValues threadSumaTemperatura=new SumTemperatureValues(subArray,listaSumaTemperaturas);
            Thread thread=new Thread(threadSumaTemperatura);
            thread.start();
            threads.add(thread);
        }
        for (Thread t: threads) t.join();
        Double mediaTemperaturas=listaSumaTemperaturas.stream().reduce(0,Integer::sum) / (double) NUMERO_TEMPERATURAS;
        System.out.println("Media: "+ mediaTemperaturas);

        Double mediaProgramaMain=temperaturas.stream().reduce(0,Integer::sum) / (double) NUMERO_TEMPERATURAS;
        System.out.println("Media desde main: "+ mediaProgramaMain);
    }

    private static ArrayList<Integer> generarArrayTemperaturas(int numeroDeTemperaturas){
        Random random=new Random();
        ArrayList<Integer> temperaturas=new ArrayList<>();
        for (int i=0;i<numeroDeTemperaturas;i++){
            temperaturas.add(random.nextInt(TEMPERATURA_MINIMA,TEMPERATURA_MAXIMA+1));
        }
        return temperaturas;
    }

    private static ArrayList<ArrayList<Integer>> dividirArrayList(ArrayList<Integer> temperaturas,int cantidadNumerosEnSubArrays){
        ArrayList<ArrayList<Integer>> listasTemperaturas=new ArrayList<>();
        ArrayList<Integer> subArray=new ArrayList<>();

        temperaturas.forEach(t -> {
            if (subArray.size()==cantidadNumerosEnSubArrays){
                listasTemperaturas.add(new ArrayList<>(subArray));
                subArray.clear();
            }

            subArray.add(t);
        });
        if (!subArray.isEmpty()) listasTemperaturas.add(new ArrayList<>(subArray));

        return listasTemperaturas;
    }
}
