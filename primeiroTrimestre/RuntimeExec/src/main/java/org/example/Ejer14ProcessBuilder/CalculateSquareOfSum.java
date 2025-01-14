package org.example.Ejer14ProcessBuilder;

import java.util.ArrayList;
import static org.example.Ejer14ProcessBuilder.Funcions.lerNumeros;


public class CalculateSquareOfSum {
    public static void main(String[] args) {
        ArrayList<Integer> numeros=lerNumeros();
        int suma= numeros.stream().reduce(0,(a,b) -> a+b);
        System.out.print(suma*suma);
    }
}
