package org.example.ejer12Pizzeria;

/*
DU2 - Exercise 12 - Pizzeria - wait() and notify()

We want to simulate a home-delivery pizzeria, where we have two kinds of processes: cooks and deliverers.

Each cook prepares pizzas as orders come in. Once a pizza is ready, the cook places it on a tray for delivery when it is possible.

Each delivery person waits for a pizza to be on the tray, removes it and takes it to the corresponding customer.
The deliverer then returns to the pizzeria and waits for a new pizza on the tray.

Additionally, each tray has a limited capacity, no more than 5 pizzas can be stored on the tray.

Each pizza must have a different identifier (starting with with 1) and a prize between 10 and 50€.

Use a linked list to model the tray. Each pizza must be delivered in the order in which it was cooked.

Stop the simulation when 100 pizzas have been cooked and delivered.

Suppose the cook takes between 500 and 1000ms to cook the pizza.

Suppose the delivery person takes between 1000 and 2000ms to deliver the pizza.

Show a message when the cook has placed a pizza on the tray.

Show a message when the delivery person has removed a pizza from the tray.

The solution must avoid deadlocks between the different threads.


 */

import java.util.LinkedList;

public class Restaurante {
    public static void main(String[] args) {
        Tray tray=new Tray();

        Cook cook=new Cook(tray);
        Deliverer deliverer=new Deliverer(tray);

        Thread threadCook=new Thread(cook);
        Thread threadDeliverer=new Thread(deliverer);

        try{
            threadCook.start();
            threadDeliverer.start();

            threadCook.join();
            threadDeliverer.join();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
