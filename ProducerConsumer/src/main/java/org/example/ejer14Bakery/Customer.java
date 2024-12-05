package org.example.ejer14Bakery;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable{
    private static AtomicInteger client_id=new AtomicInteger(1);
    private static final Random random=new Random();

    private TakeANumber takeANumber;
    private int customerId;
    private int ticketNumber;

    public Customer(TakeANumber takeANumber) {
        this.takeANumber = takeANumber;
        this.customerId=client_id.getAndIncrement();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(2000,4001));
            takeANumber.takeNumberAndWait(this);
        }catch (InterruptedException e){

        }
    }

    public int getClientId() {
        return customerId;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
