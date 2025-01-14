package org.example;

import java.io.*;
import java.util.Scanner;

public class NotepadPlusPlus {
    public static void main(String[] args) {
        Runtime runtime=Runtime.getRuntime();
        //String[] comando={"C:\\Program Files\\Notepad++\\notepad++.exe","src/main/resources/info.txt"};
        String[] comando={"cmd","/C","date"};
        try{
            Process proceso=runtime.exec(comando);

            //para os procesos que piden entrada de datos mentras se executan
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            bw.write("10-07-24"); //como o comando date pregunta a nova fecha, pasamoslla
            bw.close();

            //salida do comando, feito con scanner
            Scanner escaner=new Scanner(proceso.getInputStream());
            while(escaner.hasNextLine()){
                System.out.println(escaner.nextLine());
            }

            //espera a que o proceso acabe e devolve o estado de salida (se é distinto a 0 é que algo non fui ben)
            int exitStatus=proceso.waitFor();

            if (exitStatus!=0){
                //saida de erros feito con buffers
                BufferedInputStream bufferErros=new BufferedInputStream(proceso.getErrorStream());
                InputStreamReader isr=new InputStreamReader(bufferErros);
                BufferedReader readerErros=new BufferedReader(isr);
                String linea;
                while((linea=readerErros.readLine())!=null){
                    System.out.println(linea);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}