package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessBuilderEnviroment {
    public static void main(String[] args) {
        try{
            ProcessBuilder processBuilder=new ProcessBuilder();

            Map<String,String> enviroment = processBuilder.environment();

            enviroment.put("PROBA","Ola");

            processBuilder.command(List.of("cmd","/c","echo","%PROBA%"));

            Process process = processBuilder.start();

            try(
                    BufferedReader reader= new BufferedReader(new InputStreamReader(process.getInputStream()));
                    ){
                String linea;
                while((linea=reader.readLine())!=null){
                    System.out.println(linea);
                }
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
