package org.example.ficheirosTracert;

import org.example.FutureThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallFutureThread {
    public static void main(String[] args) {
        ArrayList<FutureTask<String>> futureTasks = new ArrayList<>();

        for (int i=0;i<10;i++){
            FutureTask<String> futureTask = new FutureTask<>(new FutureThread());
            Thread thread=new Thread(futureTask);
            thread.start();
            futureTasks.add(futureTask);
        }

        try {
            for (FutureTask<String> futureTask:futureTasks){
                System.out.println(futureTask.get());
            }
        }catch (InterruptedException | ExecutionException e){

        }
    }
}
