package org.example.ejer13Birds;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Cage {
    private static final int MAX_BIRDS_IN_PLATE=3;

    private AtomicBoolean isSwingOcupied;
    private AtomicInteger birdsInPlate;

    public Cage() {
        this.isSwingOcupied = new AtomicBoolean(false);
        this.birdsInPlate = new AtomicInteger(0);
    }

    synchronized public void eatOrSwing(Bird bird) throws InterruptedException{
        //if the bird did not eat and did not swing, it tries to eat
        if (!bird.hasEaten() && !bird.hasSwung()){
            while (this.birdsInPlate.get()>=MAX_BIRDS_IN_PLATE){
                wait();
            }

            birdsInPlate.getAndIncrement();
            bird.setEating(true);
            return;
        }

        //if it finished eating, the bird tries to swing
        if (bird.hasEaten()){
            birdsInPlate.getAndDecrement();//minus one bird eating
            notifyAll();
            bird.setHasEaten(false); //its no longer eating

            System.out.printf("\n%s finished EATING",bird.getName());

            while (this.isSwingOcupied.get()){
                wait();
            }

            this.isSwingOcupied.set(true);
            bird.setSwinging(true);
        }

        //if the bird finished swinging it notifies the other birds and goes to eat
        if (bird.hasSwung()){
            isSwingOcupied.set(false);
            notifyAll();
            bird.setHasSwung(false);

            System.out.printf("\n%s finished SWINGING",bird.getName());

        }
    }
}
