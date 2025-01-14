package org.example.Ejer7SuperEvenNumbers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NumberChecker implements Runnable{
    private static int THREAD_NUMBER=2;

    private Number number;

    public NumberChecker(Number number) {
        this.number = number;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
        String numberDigits = String.valueOf(number.getNum());
        for (int i=0;i<numberDigits.length();i++){
            int numeroEnNumerico=Character.getNumericValue(numberDigits.charAt(i));
            executorService.execute(new EvenChecker(number,numeroEnNumerico));
        }
        executorService.shutdown();
        try{
            if (executorService.awaitTermination(60, TimeUnit.SECONDS)){
                executorService.shutdownNow();
            }
        }catch (InterruptedException e){
            System.out.println("Error comprobando numero: "+number.getNum());
        }
    }
}
