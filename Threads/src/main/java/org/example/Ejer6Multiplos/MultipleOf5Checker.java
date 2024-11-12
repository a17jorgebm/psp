package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class MultipleOf5Checker implements Callable<Boolean> {
    private BigInteger number;

    public MultipleOf5Checker(BigInteger number) {
        this.number = number;
    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}