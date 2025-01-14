package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessBuilderDir {
    public static void main(String[] args) {
        try{
            ProcessBuilder processBuilder=new ProcessBuilder();
            processBuilder.directory(new File("src/main/java/org/example"));
            processBuilder.command(List.of("cmd","/c","dir"));

            Process process=processBuilder.start();

            try(
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()))
                    ){
                String linea;
                while ((linea=bufferedReader.readLine())!=null){
                    System.out.println(linea);
                }
            }
        }catch (Exception e){

        }
    }
}
