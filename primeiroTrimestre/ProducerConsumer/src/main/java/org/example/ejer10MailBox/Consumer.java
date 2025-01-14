package org.example.ejer10MailBox;

import java.util.Random;

public class Consumer implements Runnable{
    private static final Random random=new Random();

    private MailBox mailBox;

    public Consumer(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public void run() {
        while (true){
            try{
                String texto=mailBox.getMessage();
                System.out.printf("\nThread consumidor %s recibe %s",Thread.currentThread().getName(),texto);
                Thread.sleep(random.nextInt(0,1001));
            }catch (InterruptedException e){
                System.out.println("Liadote no thread consumidor "+Thread.currentThread().getName());
            }
        }
    }
}
