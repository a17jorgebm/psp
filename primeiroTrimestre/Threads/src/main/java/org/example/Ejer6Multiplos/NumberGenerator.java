package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

public class NumberGenerator implements Runnable{
    private static Random random=new Random();
    List<BigInteger> listaNumeros;

    public NumberGenerator(List<BigInteger> listaNumeros) {
        this.listaNumeros = listaNumeros;
    }

    @Override
    public void run() {
        int numberOfDigits=random.nextInt(20,51);
        StringBuilder stringBuilder=new StringBuilder();
        //o primeiro faise a parte pq non pode ser 0, asi non se fai o if no bucle
        stringBuilder.append(random.nextInt(1,10));
        for (int i=0;i<numberOfDigits-1;i++){
            stringBuilder.append(random.nextInt(0,10));
        }

        listaNumeros.add(new BigInteger(stringBuilder.toString()));
    }
}
