package org.example.Ejer7SuperEvenNumbers;

import java.util.concurrent.atomic.AtomicBoolean;

public class EvenChecker implements Runnable{
    private Number number;
    private int digitToCheck;

    public EvenChecker(Number number, int digitToCheck) {
        this.number = number;
        this.digitToCheck = digitToCheck;
    }

    @Override
    public void run() {
        if (digitToCheck%2!=0) number.setIsSuperEven(false);
        if (digitToCheck%2==0 && number.getIsSuperEven().get()) number.setIsSuperEven(true); //estupidez pero pa probar colisions
    }
}
