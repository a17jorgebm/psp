package org.example.ejer3CallablePalindrome;

/*
We want to create a multithreaded application.

The main thread must create an array of ten words. That main thread must create ten threads whose job is to check whether a word is a palindrome or not.
 A palindrome is a word, phrase, verse or even number that reads the same forwards or backwards.

 The main thread must indicate in the standard output for each word whether it is a palindrome or not.
 */

import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CheckPalindromesMultiThread {
    public static void main(String[] args) {
        String[] palabras = {"kayak", "deified", "rotator", "repaper", "deed", "peep", "wow", "noon", "civic", "racecar", "level", "mom","oli","paco"};
        HashSet<FutureTask> threads=new HashSet<>();

        for (int i=0;i<palabras.length;i++){
            FutureTask futureTask=new FutureTask(new CheckPalindromeCallable(palabras[i]));
            Thread t=new Thread(futureTask);
            t.start();
            threads.add(futureTask);
        }

        try{
            for (FutureTask f: threads){
                System.out.println((String) f.get());
            }
        }catch (InterruptedException| ExecutionException e){
            System.out.println("Erro nos threads: "+e.getMessage());
            System.exit(1);
        }

    }
}
