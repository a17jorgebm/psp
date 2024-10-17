package org.example.Ejer14ProcessBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class Funcions {
    public static ArrayList<Integer> lerNumerosMain(){
        ArrayList<Integer> numeros=new ArrayList<>();

        int cantidad=lerInt("Introduce a cantidad de números a ler: ");
        numeros.add(cantidad);
        if (cantidad<0) return null;
        for (int i=0;i<cantidad;i++){
            numeros.add(lerInt("Introduce número decimal: "));
        }
        return numeros;
    }
    public static ArrayList<Integer> lerNumeros(){
        int cantidad=lerInt("Introduce a cantidad de números a ler: ");

        if (cantidad<0) return null;
        ArrayList<Integer> numeros=new ArrayList<>();
        for (int i=0;i<cantidad;i++){
            numeros.add(lerInt("Introduce número decimal: "));
        }
        return numeros;
    }

    public static int lerInt(String mensaje){
        Scanner ler=new Scanner(System.in);
        System.out.print(mensaje);
        if (ler.hasNextInt()){
            return ler.nextInt();
        }
        return lerInt("Error, debe ser un int: ");
    }
}
