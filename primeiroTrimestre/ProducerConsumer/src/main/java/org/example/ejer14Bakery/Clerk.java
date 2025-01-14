package org.example.ejer14Bakery;

import java.util.Random;

public class Clerk implements Runnable{



    private TakeANumber takeANumber;

    public Clerk(TakeANumber takeANumber) {
        this.takeANumber = takeANumber;
    }

    @Override
    public void run() {
        try{
            takeANumber.callCustomers();
        }catch (InterruptedException e){

        }
    }
}
