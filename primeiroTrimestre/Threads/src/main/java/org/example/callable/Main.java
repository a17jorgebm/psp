package org.example.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] nomes={"pepe","noelia"};
        Callable callable=new GreetCallable(nomes[0]);
        FutureTask futureTask=new FutureTask(callable);
        Thread thread=new Thread(futureTask);
        thread.start();
        String msg=(String) futureTask.get();
        System.out.println(msg);
    }
}
