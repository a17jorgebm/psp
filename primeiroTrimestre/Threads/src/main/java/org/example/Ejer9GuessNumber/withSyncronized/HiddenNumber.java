package org.example.Ejer9GuessNumber.withSyncronized;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class HiddenNumber {
    private final Random random=new Random();

    private int numHidden;
    private boolean gameEnded;

    public HiddenNumber() {
        this.gameEnded=false;
        this.numHidden = random.nextInt(0,101);
    }

    public synchronized int numberGuess(int num){
        if (gameEnded) return -1;
        if (num==numHidden){
            this.gameEnded=true;
            return 1;
        }
        return 0;
    }


}
