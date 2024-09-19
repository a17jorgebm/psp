package org.example;

import java.util.Scanner;

public class BirthDayStdIn {
    public static void main(String[] args) {
        System.out.print("Cuantos casos vas a hacer: ");
        Scanner ler=new Scanner(System.in);
        int numeroCasos=ler.nextInt();

        for (int i=0;i<=numeroCasos;i++){
            System.out.print("Introduce tu edad en segundos: ");
            int numero= ler.nextInt();
            int contadorVelas=0;
            while(true){
                if (numero==0) { break; }
                int resto=numero%2; 
                numero=numero/2;

                if (resto==1){ contadorVelas++; }
            }
            System.out.println(contadorVelas);
        }
    }
}