package ejer6GuessANumberMultiThread.Server;

import java.util.Random;

public class Game {
    private static final int MIN_NUMBER_IN_RANGE = 0;
    private static final int MAX_NUMBER_IN_RANGE = 100;
    private static final Random random = new Random();

    private int attemptsLeft;
    private int numberToGuess;
    private boolean gameEnded;
    private boolean userWon;

    public Game(int attemptsLeft) {
        this.attemptsLeft=attemptsLeft;
        numberToGuess=random.nextInt(MIN_NUMBER_IN_RANGE,MAX_NUMBER_IN_RANGE+1);
        gameEnded=false;
        userWon=false;
    }

    public boolean guessNumber(int number){
        //good guess
        if (number==numberToGuess){
            gameEnded=true;
            userWon=true;
            return true;
        }

        //bad guess
        --attemptsLeft;
        if (attemptsLeft == 0){ //game ended
            gameEnded=true;
        }
        return false;
    }

    public boolean gameHasEnded(){
        return gameEnded;
    }

    public boolean userWon(){
        return userWon;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }
}
