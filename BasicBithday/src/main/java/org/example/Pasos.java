package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Pasos {
    private static Scanner ler;
    public static void main(String[] args){
        LinkedList<LinkedList<Integer>> valores=new LinkedList<>();

        //comprobación numero argumentos


        //comprobación tipo de argumentos
        LinkedList<Integer> argsInt=new LinkedList<>();
        for (String arg:args){
            try{
                argsInt.add(Integer.parseInt(arg));
            }catch(Exception e){
                System.out.println("Todos los argumentos deben ser enteros");
            }
        }
        Integer numeroCasos=argsInt.pollFirst();

        if (numeroCasos*2!=argsInt.size()){
            System.out.println("Número de argumentos introducidos no coincide con el indicado!");
            return;
        }
    }
}
