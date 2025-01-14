package org.example;

/*
DU1 - Exercise 6 - Java ProcessBuilder - dir

Create a program to show what the default directory of execution of a process is. Use the directory()
 method of ProcessBuilder.

Execute a process that shows the result of the dir command in the C directory.

Request a path to the user and execute a process that shows the result of the dir command in that path.
 */

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Ejer6Dir {
    public static void main(String[] args) {
        apartado3();
    }

    public static void apartado1(){
        ProcessBuilder pb=new ProcessBuilder();
        pb.directory(new File("")); //directorio raiz do proxecto
        System.out.println(pb.directory().getAbsolutePath());
    }

    public static void apartado2(){
        try{
            ProcessBuilder pb=new ProcessBuilder();
            pb.directory(new File("C:\\"));
            pb.command("cmd","/c","dir");
            Process proceso=pb.start();
            BufferedReader reader=new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while((linea=reader.readLine()) != null){
                System.out.println(linea);
            }
            int codigoExit=proceso.waitFor();
            if (codigoExit!=0){
                System.out.println("Ocorriu un erro: "+codigoExit);
            }
        }catch (Exception e){

        }
    }
    public static void apartado3(){
        try{
            //leese o directorio
            JFileChooser fc=new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (fc.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION){
                System.out.println("Non se escolliu nada, saindo...");
                System.exit(0);
            }
            File file=fc.getSelectedFile();
            //crease o proceso
            ProcessBuilder pb=new ProcessBuilder();
            pb.directory(file);
            pb.command("cmd","/c","dir");
            Process proceso=pb.start();
            //leese a salida
            StringBuilder sb=new StringBuilder();
            BufferedReader reader=new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while((linea=reader.readLine()) != null){
                sb.append("\n");
                sb.append(linea);
            }
            System.out.print(sb.toString());
            //errores
            int codigoExit=proceso.waitFor();
            if (codigoExit!=0){
                System.out.println("Ocorriu un erro: "+codigoExit);
            }
        }catch (Exception e){

        }
    }
}
