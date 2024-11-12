package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.Map;

public class CheckMultipleOf3Thread implements Runnable{
    private BigInteger number;
    private Map<BigInteger, Map<Integer, Boolean>> mapOfNumbers;

    public CheckMultipleOf3Thread(BigInteger number, Map<BigInteger, Map<Integer, Boolean>> mapOfNumbers) {
        this.number = number;
        this.mapOfNumbers = mapOfNumbers;
    }

    @Override
    public void run() {
        String numberInText=number.toString();
        BigInteger suma=BigInteger.ZERO;

        for (int i=0;i<numberInText.length();i++){
            int num=Character.getNumericValue(numberInText.charAt(i));
            suma=suma.add(BigInteger.valueOf(num));
        }

        mapOfNumbers.get(number).put(
                3,
                suma.remainder(new BigInteger("3")).equals(BigInteger.ZERO)
        );
    }
}
