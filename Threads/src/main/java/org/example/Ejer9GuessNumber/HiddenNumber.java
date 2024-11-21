package org.example.Ejer9GuessNumber;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class HiddenNumber {
    private final Random random=new Random();

    private int numHidden;
    private AtomicBoolean gameEnded;

    public HiddenNumber() {
        this.gameEnded=new AtomicBoolean(false);
        this.numHidden = random.nextInt(0,101);
    }

    public int numberGuess(int num){
        if (gameEnded.get()) return -1;
        if (num==numHidden){
            this.gameEnded.set(true);
            return 1;
        }
        return 0;
    }


}
