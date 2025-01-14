package org.example;

import java.util.Map;

/*
DU1 - Exercise 7 - Java ProcessBuilder - echo

Create a program to show the execution environment of a process with the
 ProcessBuilder method Map<String,String> environment().

Execute the process created to echo "Hello World" in the command line.
 */
public class Ejer7Echo {
    public static void main(String[] args) {
        try{
            ProcessBuilder pb=new ProcessBuilder();
            Map<String,String> entorno= pb.environment();
            entorno.put("OLA","Hello World");
            StringBuilder sb=new StringBuilder();
            sb.append("\nClaves de entorno del proceso:");
            sb.append("\n\n-------------------------------------");
            entorno.forEach((clave,valor) -> sb.append("\n"+clave + "="+ valor));
            sb.append("\n\n-------------------------------------");
            System.out.print(sb.toString());
        }catch (Exception e){

        }
    }
}
