package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.Map;

public class CheckMultipleOf11Thread implements Runnable{
    private BigInteger number;
    private Map<BigInteger, Map<Integer, Boolean>> mapOfNumbers;

    public CheckMultipleOf11Thread(BigInteger number, Map<BigInteger, Map<Integer, Boolean>> mapOfNumbers) {
        this.number = number;
        this.mapOfNumbers = mapOfNumbers;
    }

    @Override
    public void run() {
        String numberInText=number.toString();
        BigInteger sumOdds=BigInteger.ZERO;
        BigInteger sumEven=BigInteger.ZERO;

        for (int i=0;i<numberInText.length();i++){
            BigInteger n=BigInteger.valueOf(Character.getNumericValue(numberInText.charAt(i)));
            if (i%2==0){
                sumEven=sumEven.add(n);
            }else {
                sumOdds=sumOdds.add(n);
            }
        }
        BigInteger difference = sumOdds.subtract(sumEven);

        mapOfNumbers.get(number).put(
                11,
                difference.equals(BigInteger.ZERO) //if the value its 0 we dont do the subtraction(an error will happen)
                        || difference.remainder(new BigInteger("3")).equals(BigInteger.ZERO)
        );
    }
}