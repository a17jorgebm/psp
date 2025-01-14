package org.example.Ejer9GuessNumber.withSyncronized;

import java.util.Random;

public class GuessNumber implements Runnable{
    private static final Random random=new Random();
    private HiddenNumber hiddenNumber;

    public GuessNumber(HiddenNumber hiddenNumber) {
        this.hiddenNumber = hiddenNumber;
    }

    @Override
    public void run() {
        while (true){
            int numeroMetido=random.nextInt(0,101);
            int resultado=hiddenNumber.numberGuess(numeroMetido);

            if (resultado==0) {
                System.out.println("Thread "+Thread.currentThread().getName() + "non adivinou con "+numeroMetido);
                continue;
            };
            if (resultado==-1){
                System.out.println("Thread "+Thread.currentThread().getName() + "terminado. Numero: "+numeroMetido);
                break;
            }
            System.out.println("ADIVINADO! Por el thread "+Thread.currentThread().getName()+ ". Numero: "+numeroMetido);
            break;
        }
    }
}
