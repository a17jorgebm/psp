package org.example;

import java.util.Scanner;

public class ProgramaConInputPorTeclado {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.print("Mete un numero: ");
        int numero = scanner.nextInt();

        System.out.println(numero);
    }
}
