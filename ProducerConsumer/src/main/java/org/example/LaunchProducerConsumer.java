package org.example;

public class LaunchProducerConsumer {
    public static void main(String[] args) {
        Resource resource=new Resource();
        Producer producer=new Producer(resource);
        Consumer consumer=new Consumer(resource);

        Thread thProducer=new Thread(producer);
        Thread thConsumer=new Thread(consumer);

        thProducer.start();
        thConsumer.start();

        try{
            thProducer.join();
            thConsumer.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}
