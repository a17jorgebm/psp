package org.example.ejer12Pizzeria;

import java.util.List;
import java.util.Random;

public class Cook implements Runnable{
    private static final Random random=new Random();

    private Tray tray;

    public Cook(Tray tray) {
        this.tray = tray;
    }

    @Override
    public void run() {
        while (!tray.stopStopCooking()){
            int timeToWait=random.nextInt(500,1001);
            Pizza pizza=new Pizza(Pizza.generateId(), random.nextFloat(10,51));
            System.out.printf("\n%s: cooking pizza %d of %.2f€. Should take about %dms",
                    Thread.currentThread().getName(),pizza.getId(),pizza.getPrecio(),timeToWait);
            try{
                Thread.sleep(timeToWait);
                tray.addPizza(pizza);
                System.out.printf("\nThe cooker %s added the pizza %s : %.2f",Thread.currentThread().getName(),pizza.getId(),pizza.getPrecio());
            }catch (InterruptedException e){
                System.out.printf("\nERROR: The cooker %s couldnt add the pizza %s : %.2f",Thread.currentThread().getName(),pizza.getId(),pizza.getPrecio());
            }
        }
    }
}
