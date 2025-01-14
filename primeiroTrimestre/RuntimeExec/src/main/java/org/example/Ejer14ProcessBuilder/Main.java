package org.example.Ejer14ProcessBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
DU1 - Exercise 14 - Java ProcessBuilder

Create a program in Java that reads from the standard input a number and after it reads the quantity
of numbers indicated above.  The program must add up all the numbers and calculate the square of the
 sum and display the result on the standard output.

Create another program in Java that reads from the standard input a number and after it reads the
quantity of numbers indicated above.  The program must square all of the numbers and then calculate
their sum and display the result on the standard output.

Finally, create another program that launches the two programs created earlier using the same numbers
 and display the result on the standard output. Use getInputStream and getOutputStream to communicate with the processes.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numerosParaArgumentos=Funcions.lerNumerosMain();
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("java", "-cp", "./target/classes", "org.example.Ejer14ProcessBuilder.CalculateSquareOfSum");

        Process proceso = null;
        try {
            proceso=builder.start();
            //proceso.waitFor();
        } catch (IOException  e) {
            e.printStackTrace();
        }

        int a=0;

        try (
                BufferedWriter output=new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                BufferedReader readerErros = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))
        ) {

            for (int numero:numerosParaArgumentos){
                output.write(numero);
            }
            output.flush();

            int i=0;
            String leido;
            while ((leido=reader.readLine())!=null){
                System.out.println(leido);
                //output.write(numerosParaArgumentos.get(i++));
            }
            String erro;
            while ((erro=readerErros.readLine())!=null){
                System.out.println(erro);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
