package org.example.ejer12Pizzeria;

import java.util.List;
import java.util.Random;

public class Deliverer implements Runnable{
    private static final Random random=new Random();


    private Tray tray;

    public Deliverer(Tray tray) {
        this.tray = tray;
    }

    @Override
    public void run() {
        while (true){
            Pizza pizza=null;
            int timeToDeliver=random.nextInt(1000,20001);

            try{
                pizza=tray.getPizzaToDeliver();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }

            if (pizza==null)
                break;

            System.out.printf("\nDelivering the pizza %s : %.2f, by deliverer %s. Taking about %dms"
                    ,pizza.getId(),pizza.getPrecio(),Thread.currentThread().getName(),timeToDeliver);

            try{
                Thread.sleep(timeToDeliver);
            }catch (InterruptedException e){

            }
        }
    }
}
