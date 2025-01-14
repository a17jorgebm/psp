package org.example.ejer10MailBox;

/*

DU2- Exercise 10 - MailBox - wait() and notify()

Suppose we want to implement a synchronized application with a Mailbox class that allows one thread to deposit a message and
another thread to pick it up. But, there can only be one message in the mailbox at a time. If a thread tries to deposit a message
hen there is already one, it must wait until it is collected. And if a thread tries to pick up a message when there is none, it
must wait until one is deposited.

After depositing or collecting a message, each thread sleeps for a random time between 0 and 1sec to repeat the cycle again.

First, the application must work with one thread depositing messages and one thread collecting messages.

Finally, the application must run with five threads of each type.

 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static int NUMBER_OF_THREADS=5;

    public static void main(String[] args) throws InterruptedException{
        launchMultipleThreads();
    }

    public static void launchWithOneThreadOfEach() throws InterruptedException{
        MailBox mailBox=new MailBox();
        Producer producer=new Producer(mailBox);
        Consumer consumer=new Consumer(mailBox);

        Thread threadProducer=new Thread(producer);
        Thread threadConsumer=new Thread(consumer);

        threadProducer.start();
        threadConsumer.start();

        threadProducer.join();
        threadConsumer.join();
    }

    public static void launchMultipleThreads() throws InterruptedException{
        ExecutorService threadPoolProducers= Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        ExecutorService threadPoolConsumers= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        MailBox mailBox=new MailBox();
        for (int i=0;i<NUMBER_OF_THREADS;i++){
            Thread threadProducer=new Thread(new Producer(mailBox));
            Thread threadConsumer=new Thread(new Consumer(mailBox));

            threadPoolProducers.execute(threadProducer);
            threadPoolConsumers.execute(threadConsumer);
        }

        threadPoolConsumers.shutdown();
        threadPoolProducers.shutdown();
    }
}
