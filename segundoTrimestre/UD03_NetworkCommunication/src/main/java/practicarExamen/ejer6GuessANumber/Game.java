package practicarExamen.ejer6GuessANumber;

import java.util.Random;

public class Game {
    private static final Random RANDOM=new Random();

    private int remainingLifes;
    private int numberToGuess;
    private boolean gameEnded;
    private boolean userWon;

    public Game(int remainingLifes) {
        this.remainingLifes = remainingLifes;
        numberToGuess = RANDOM.nextInt(0,101);
    }

    /**
     *
     * @param number The number to try
     * @return -1 if the correct number is lower; 0 if it is the correct number; and 1 if the correct number is higher
     */
    public int guessNumber(int number){
        int result = Integer.compare(numberToGuess, number);
        if (result!=0){
            if (--remainingLifes<=0) gameEnded=true;
        }else {
            userWon=true;
            gameEnded=true;
        }
        return result;
    }

    public int getRemainingLifes() {
        return remainingLifes;
    }

    public int getNumberToGuess() {
        return numberToGuess;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public boolean isUserWon() {
        return userWon;
    }
}
