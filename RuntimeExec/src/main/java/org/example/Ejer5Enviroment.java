package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

/*
DU1 - Exercise 5 - Java ProcessBuilder - set - environment

Create a program in Java using the ProcessBuilder class to configure a process.
The process has to use the command set to add two numbers.
Use two new environment variables to indicate the numbers to add.
 */
public class Ejer5Enviroment {
    public static void main(String[] args) {
        try{
            ProcessBuilder processBuilder=new ProcessBuilder();

            Map<String,String> enviroment=processBuilder.environment();

            //creamos as variables de entorno para usalas no comando
            enviroment.put("NUM1","10");
            enviroment.put("NUM2","40");

            //creamos o comando
            processBuilder.command("cmd.exe","/c","set NUM1 & set NUM2 & set /a RESULT=%NUM1%+%NUM2%");

            Process process=processBuilder.start();

            BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line=reader.readLine()) != null){
                System.out.println(line);
            }

            int exitCode=process.waitFor();
            System.out.println("Process exited with code: "+exitCode);
        }catch (Exception e){

        }

    }
}
