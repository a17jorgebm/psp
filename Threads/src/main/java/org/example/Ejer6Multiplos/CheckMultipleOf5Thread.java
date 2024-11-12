package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.Map;

public class CheckMultipleOf5Thread implements Runnable{
    private BigInteger number;
    private Map<BigInteger, Map<Integer, Boolean>> mapOfNumbers;

    public CheckMultipleOf5Thread(BigInteger number, Map<BigInteger, Map<Integer, Boolean>> mapOfNumbers) {
        this.number = number;
        this.mapOfNumbers = mapOfNumbers;
    }

    @Override
    public void run() {
        Character lastChar=number.toString().charAt(number.toString().length()-1);
        mapOfNumbers.get(number).put(
                5,
                lastChar.compareTo('5')==0 || lastChar.compareTo('0') ==0
                );
    }
}
