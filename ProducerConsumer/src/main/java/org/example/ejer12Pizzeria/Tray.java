package org.example.ejer12Pizzeria;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Tray {
    private List<Pizza> tray;
    private int numberOfCookedPizzas;
    private AtomicBoolean stopCooking;
    private AtomicBoolean stopDelivering;


    public Tray() {
        this.tray = new LinkedList<>();
        this.numberOfCookedPizzas=0;

        this.stopCooking=new AtomicBoolean(false);
        this.stopDelivering=new AtomicBoolean(false);
    }

    synchronized public void addPizza(Pizza pizza) throws InterruptedException{
        while (tray.size()>=5){
            wait();
        }
        if (stopCooking.get()){
            return;
        }

        tray.add(pizza);
        numberOfCookedPizzas++;
        System.out.println(tray.size());
        if (numberOfCookedPizzas==100) stopCooking.set(true);
        notifyAll();
    }

    synchronized public Pizza getPizzaToDeliver() throws InterruptedException{
        while (tray.isEmpty()){
            wait();
        }
        if (stopDelivering.get()) return null;

        Pizza pizza= tray.removeFirst();
        if (stopCooking.get() && tray.isEmpty()){
            stopDelivering.set(true);
        }

        notifyAll();
        return pizza;
    }

    public boolean stopDelivering() {
        return stopDelivering.get();
    }

    public boolean stopStopCooking() {
        return stopCooking.get();
    }
}
