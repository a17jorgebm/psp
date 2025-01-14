package org.example;

public class Resource {
    private int content;
    private boolean available=false;
    public synchronized void put(int content) throws InterruptedException{
        while (available){
            wait();
        }
        this.content=content;
        available=true;
        notifyAll();
    }

    public synchronized int get() throws InterruptedException{
        while (!available){
            wait();
        }
        available=false;
        return content;
    }
}
