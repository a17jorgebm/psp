package org.example.ejer14Bakery;

import java.util.Random;

public class Clerk implements Runnable{
    private static final int MAX_CLIENTS_TO_SERVE=100;
    private static final Random random=new Random();


    private TakeANumber takeANumber;

    public Clerk(TakeANumber takeANumber) {
        this.takeANumber = takeANumber;
    }

    @Override
    public void run() {
        for (int i=0;i<MAX_CLIENTS_TO_SERVE;i++){
            try {
                takeANumber.callCustomer();
                Thread.sleep(random.nextInt(1001));
            }catch (InterruptedException e){

            }
        }

    }
}
