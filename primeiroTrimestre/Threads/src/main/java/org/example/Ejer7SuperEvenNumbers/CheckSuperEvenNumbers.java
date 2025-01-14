package org.example.Ejer7SuperEvenNumbers;

/*
DU2 - Exercise 7 - Java Threads - Atomic variables - Super even numbers


A super even number is a whole number in which every digit is even. This means that each digit in the number must be one of the even digits: 0, 2, 4, 6, or 8.

Given a set of numbers, we try to determine whether or not they are super even or not by creating a multithreading application.

The main thread must generate two random numbers (use java.util.Random) between 1 and 10000 to determine the numbers we want to check.
That main thread must create a pool of four threads.

The main thread must check the numbers between the smallest and the largest of the randomly generated numbers.

For each number it will display a single line showing whether the number is a super even.

Each of the threads created by the main thread must create a thread pool of two threads to check each of the digits of the number.
These threads must share an object named Number where the number is stored and a boolean that informs whether or not the number is super even.

Review the following information if you need to wait for threads to finish:

https://ducmanhphan.github.io/2020-03-20-Waiting-threads-to-finish-completely-in-Java/

Follow the given specifications to create the application.
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CheckSuperEvenNumbers {
    private static final int NUM_THREADS=4;

    public static void main(String[] args) throws IllegalArgumentException{
        Random random=new Random();
        int num1=random.nextInt(1,1001);
        int num2=random.nextInt(1,1001);

        int numMinimo=Math.min(num1,num2);
        int numMaximo=Math.max(num1,num2);

        ArrayList<Number> numbers=new ArrayList<>();

        ExecutorService executor= Executors.newFixedThreadPool(NUM_THREADS);

        for (int i=numMinimo;i<=numMaximo;i++){
            Number number=new Number(i);
            numbers.add(number);
            executor.execute(new NumberChecker(number));
        }
        executor.shutdown();
        try{
            if (executor.awaitTermination(10, TimeUnit.SECONDS)){
                executor.shutdownNow();
            }
        }catch (InterruptedException e){
            System.out.println("Erro comprobando os numeros:"+e.getMessage());
            System.exit(1);
        }

        numbers.forEach(n -> {
            System.out.printf("%d: %s\n",n.getNum(),n.getIsSuperEven().get() ? "É superpar" : "Non é superpar");
        });

    }
}
