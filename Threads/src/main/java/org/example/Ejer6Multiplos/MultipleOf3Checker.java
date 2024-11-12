package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class MultipleOf3Checker implements Callable<Boolean> {
    private BigInteger number;

    public MultipleOf3Checker(BigInteger number) {
        this.number = number;
    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}
