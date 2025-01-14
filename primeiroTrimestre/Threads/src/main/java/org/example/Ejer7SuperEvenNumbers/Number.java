package org.example.Ejer7SuperEvenNumbers;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Number {
    private int num;
    private AtomicBoolean isSuperEven;

    public Number(int num) {
        this.num = num;
        this.isSuperEven=new AtomicBoolean(true);
    }

    public int getNum() {
        return num;
    }

    public AtomicBoolean getIsSuperEven() {
        return isSuperEven;
    }

    public void setIsSuperEven(boolean isSuperEven) {
        this.isSuperEven.set(isSuperEven);
    }
}
