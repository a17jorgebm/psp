package org.example;

import java.util.LinkedList;

public class EntradaPorConsola {
    public static void main(String[] args) {
        //comprobación numero argumentos
        if(args.length<2){
            System.out.println("Se deben introducir 2 argumentos como mínimo");
            return;
        }
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

        if (numeroCasos!=args.length-1){
            System.out.println("Número de casos introducidos no coincide con el pedido!");
            return;
        }

        for (Integer num:argsInt){
            int contador=0;
            String binario= Integer.toBinaryString(num);
            for (int i=0;i<binario.length();i++){
                if (binario.charAt(i) == '1'){ contador++; }
            }
            System.out.println(contador);
        }
    }


}
