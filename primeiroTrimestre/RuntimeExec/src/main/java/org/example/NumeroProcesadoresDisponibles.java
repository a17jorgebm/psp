package org.example;

public class NumeroProcesadoresDisponibles {
    public static void main(String[] args) {
        Runtime runtime=Runtime.getRuntime();
        System.out.println("Procesadores dispoñibles da JVM: "+runtime.availableProcessors());
    }
}
