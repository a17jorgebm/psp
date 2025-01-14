package org.example.ejer13Birds;

import java.util.Random;

public class Bird implements Runnable{
    private static final int MIN_TIME_EATING=500;
    private static final int MAX_TIME_EATING=1000;
    private static final int MIN_TIME_SWINGING=500;
    private static final int MAX_TIME_SWINGING=2000;
    private static final Random random=new Random();

    private boolean isEating;
    private boolean isSwinging;
    private boolean hasEaten;
    private boolean hasSwung;

    private Cage cage;
    private String name;

    public Bird(Cage cage, String name) {
        this.cage = cage;
        this.name=name;
        this.isEating=false;
        this.isSwinging=false;
    }

    @Override
    public void run() {
        while (true){
            try {
                cage.eatOrSwing(this);
            }catch (InterruptedException e){

            }

            if (this.isEating){
                int timeEating=random.nextInt(MIN_TIME_EATING,MAX_TIME_EATING);
                System.out.printf("\n%s is EATING for %dms",this.name,timeEating);
                try{
                    Thread.sleep(timeEating);
                }catch (InterruptedException e){

                }
                this.setEating(false);
                this.setHasEaten(true);
            }
            if (this.isSwinging){
                int timeSwinging=random.nextInt(MIN_TIME_SWINGING,MAX_TIME_SWINGING);
                System.out.printf("\n%s is SWINGING for %dms",this.name,timeSwinging);
                try{
                    Thread.sleep(timeSwinging);
                }catch (InterruptedException e){

                }
                this.setSwinging(false);
                this.setHasSwung(true);
            }
        }
    }

    public boolean isEating() {
        return isEating;
    }

    public void setEating(boolean eating) {
        isEating = eating;
    }

    public boolean isSwinging() {
        return isSwinging;
    }

    public void setSwinging(boolean swinging) {
        isSwinging = swinging;
    }

    public boolean hasEaten() {
        return hasEaten;
    }

    public void setHasEaten(boolean hasEaten) {
        this.hasEaten = hasEaten;
    }

    public boolean hasSwung() {
        return hasSwung;
    }

    public void setHasSwung(boolean hasSwung) {
        this.hasSwung = hasSwung;
    }

    public String getName() {
        return name;
    }
}
