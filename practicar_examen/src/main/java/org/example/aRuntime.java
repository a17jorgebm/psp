package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class aRuntime {
    public static void main(String[] args) throws IOException,InterruptedException{
        Runtime runtime= Runtime.getRuntime();

        String[] command = {
                "cmd","/c","tasklist","/NH","|","findstr","/I","chrome"
        };

        Process process=runtime.exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String linea;
        while ((linea=reader.readLine())!=null){
            System.out.println(linea);
        }
    }
}
