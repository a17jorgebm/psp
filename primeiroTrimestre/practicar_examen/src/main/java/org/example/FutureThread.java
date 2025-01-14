package org.example;

import java.util.concurrent.Callable;

public class FutureThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "pitiño";
    }
}
