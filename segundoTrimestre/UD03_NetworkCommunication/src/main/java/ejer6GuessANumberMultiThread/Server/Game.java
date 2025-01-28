package ejer6GuessANumberMultiThread.Server;

import java.util.Random;

public class Game {
    private static final int MIN_NUMBER_IN_RANGE = 0;
    private static final int MAX_NUMBER_IN_RANGE = 100;
    private static final Random random = new Random();

    private int attemptsLeft;
    private int numberToGuess;

    public Game(int attemptsLeft) {
        this.attemptsLeft=attemptsLeft;
        numberToGuess=random.nextInt(MIN_NUMBER_IN_RANGE,MAX_NUMBER_IN_RANGE+1);
    }

    public boolean guessNumber(int number){
        if (number==numberToGuess){
            return true;
        }
        --attemptsLeft;
        return false;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }
}
