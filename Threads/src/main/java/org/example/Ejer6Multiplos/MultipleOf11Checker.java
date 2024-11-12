package org.example.Ejer6Multiplos;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class MultipleOf11Checker implements Callable<Boolean> {
    private BigInteger number;

    public MultipleOf11Checker(BigInteger number) {
        this.number = number;
    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}