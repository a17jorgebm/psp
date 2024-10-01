package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class MatarProceso {
    public static final String comandoMatarProceso= "taskkill /F /PID ((Get-Process %s).id)";
    public static void main(String[] args) {
        Scanner ler=new Scanner(System.in);
        System.out.print("Introduce o nombre do comando a matar: ");
        String nomeComando=ler.nextLine();

        if (nomeComando.isBlank()){
            System.out.println("Nome de comando non válido, saíndo do programa...");
            System.exit(1);
        }

        String[] comando={
                "powershell",
                "/c",
                String.format(comandoMatarProceso,nomeComando)
        };

        Runtime runtime=Runtime.getRuntime();
        try{
            Process proceso=runtime.exec(comando);
        }catch (Exception e){

        }
    }
}
