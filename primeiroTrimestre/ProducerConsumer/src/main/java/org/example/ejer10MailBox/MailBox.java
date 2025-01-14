package org.example.ejer10MailBox;

public class MailBox {
    private String message;
    private boolean available;

    public synchronized void putMessage(String message) throws InterruptedException{
        while (available){
            wait();
        }
        available=true;
        this.message=message;
        notifyAll();
    }

    public synchronized String getMessage() throws InterruptedException{
        if (!available){
            wait();
        }
        available=false;
        notifyAll();
        return this.message;
    }
}
