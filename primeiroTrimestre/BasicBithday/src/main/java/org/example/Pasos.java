package org.example;

import java.util.LinkedList;

/*
DU1 - Review Exercise 3 - Java - Git repository - Steps

Create a Java program for the following scenario by creating a maven project in IntellijIDEA.

Add the project to a Git repository. Create a README.md.

Suppose a person who is able to climb several steps in one go.
This ability has led them to travel the world participating in so-called vertical races, such as the world's oldest and most famous tower race in which participants must climb the 1576 steps of the Empire State Building.
A normal person would need 1576 steps to climb all those steps. Climbing them two at a time, 788 would be enough, and three at a time only 526.

Input
The first number in the input indicates how many test cases are to be processed.

It is followed by a line for each test case, with two numbers between 1 and 1,000,000. The first one indicates the number of steps and the second one indicates how many steps can be climbed at a time.

Output
For each test case the minimum number of jumps needed to climb all the rungs shall be written.

Example of input
5
1576 1
1576 2
1576 3
4000 1999
3 4

Example of output
1576
788
526
3
1

Use Javadoc comments to document the program.

The final version of the project must be in the Git repository.

Execute the program from the command line.
 */

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
