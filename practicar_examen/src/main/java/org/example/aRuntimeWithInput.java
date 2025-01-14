package org.example;

import java.io.*;

public class aRuntimeWithInput {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime=Runtime.getRuntime();

        String[] command = {
                "cmd",
                "/c",
                "java",
                "-cp",
                "target/classes",
                "org.example.proba"
        };

        Process process=runtime.exec(command);

        try(
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))
        ){
            String line;
            while ((line=reader.readLine())!=null){
                if (line.equals("Mete un numero:")){

                }

                System.out.println(line);
            }
        }


        int error = process.waitFor();
        if (error!=0){
            try(
                    BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    ){
                String errorLine;
                while ((errorLine=errorReader.readLine())!=null){
                    System.out.println(errorLine);
                }
            }
        }
    }
}
