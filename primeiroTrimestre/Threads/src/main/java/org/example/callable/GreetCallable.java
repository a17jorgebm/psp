package org.example.callable;

import java.util.concurrent.Callable;

public class GreetCallable implements Callable {
    private String persoa;

    public GreetCallable(String persoa) {
        this.persoa = persoa;
    }

    @Override
    public Object call() throws Exception {
        return persoa+ "ola!";
    }
}
