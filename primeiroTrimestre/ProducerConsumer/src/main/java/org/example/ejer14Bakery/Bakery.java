package org.example.ejer14Bakery;

/*
DU2 - Exercise 14 - Bakery tickets - wait() and notify()

A clerk in a bakery serves customers in the order they took their tickets. Customers take tickets in a random order.

The program must simulate that 100 clients want to be served by the clerk. When the clerk finishes serving the
clients, the simulation must end.

The clerk takes a random time of no more than 1000ms to serve a customer.

Each client waits a random time between 20000 and 40000 milliseconds to take a number after entering the bakery.

You have to use four classes in this project: Bakery (main), TakeANumber (shared resource), Clerk and Customer.

The messages to be displayed during execution are:

    Starting clerk and customer threads (simulation begins)
    Clerk waiting (there are no clients to serve)
    Customer #numClient takes ticket #numTicket
    Clerk serving ticket #numTicket

 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bakery {

    private static final int NUM_OF_CLIENTS=100;

    public static void main(String[] args) {
        TakeANumber takeANumber=new TakeANumber();

        Clerk clerk=new Clerk(takeANumber);
        Thread threadClerk=new Thread(clerk);
        threadClerk.start();

        ExecutorService customersPool= Executors.newFixedThreadPool(NUM_OF_CLIENTS);
        for (int i=0;i<NUM_OF_CLIENTS;i++){
            Customer customer=new Customer(takeANumber);
            Thread customerThread=new Thread(customer);
            customersPool.execute(customerThread);
        }
        customersPool.shutdown();
    }
}
