package org.example;

import java.util.LinkedList;

public class Pasos {
    public static void main(String[] args){
        //comprobación numero argumentos
        Integer numeroCasos=null;
        try{
            numeroCasos=Integer.parseInt(args[0]);
        }catch (Exception e){
            System.out.println("Argumentos inválidos");
            return;
        }
        if (numeroCasos*2!=args.length-1){
            System.out.println("Número de argumentos introducidos no coincide con el indicado!");
            return;
        }

        //comprobación tipo de argumentos
        LinkedList<Integer> argsInt=new LinkedList<>();
        for (String arg:args){
            try{
                argsInt.add(Integer.parseInt(arg));
            }catch(Exception e){
                System.out.println("Todos los argumentos deben ser enteros");
                return;
            }
        }
        argsInt.pollFirst();//quito el primero

        for (int i=0;i<argsInt.size();i+=2){
            int numeroPasos=0;
            try{
                numeroPasos=argsInt.get(i)/argsInt.get(i+1);
                if (argsInt.get(i)%argsInt.get(i+1)>0){
                    ++numeroPasos;
                }
            }catch (Exception e){
                System.out.println("Error al hacer el calculo");
                continue;
            }
            System.out.println(numeroPasos);
        }
    }
}
