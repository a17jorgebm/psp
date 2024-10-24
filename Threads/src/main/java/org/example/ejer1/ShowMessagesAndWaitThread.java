package org.example.ejer1;

import java.util.Random;

public class ShowMessagesAndWaitThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Current thread name: "+Thread.currentThread().getName());

        Random random=new Random();
        for (int i=0;i<5;i++){
            int num=random.nextInt(10,501);
            try {
                Thread.currentThread().sleep(num);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Adios desde "+Thread.currentThread().getName());
    }
}
