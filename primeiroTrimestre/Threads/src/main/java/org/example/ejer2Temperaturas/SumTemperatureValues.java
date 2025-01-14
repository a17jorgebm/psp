package org.example.ejer2Temperaturas;

import java.util.ArrayList;

public class SumTemperatureValues implements Runnable{
    private ArrayList<Integer> listaTemperaturas;
    private ArrayList<Integer> listaSumas;

    public SumTemperatureValues(ArrayList<Integer> listaTemperaturas, ArrayList<Integer> listaSumas) {
        this.listaTemperaturas = listaTemperaturas;
        this.listaSumas = listaSumas;
    }

    @Override
    public void run() {
        int suma=listaTemperaturas.stream().reduce(0,  Integer::sum);
        listaSumas.add(suma);
    }
}
