package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RunTimeBirthDay {
    private static final String rutaJar=".\\src\\main\\resources\\BasicBithday-1.0-SNAPSHOT.jar";
    private static final String nombrePaquete="org.example.EntradaPorConsola";
    public static void main(String[] args) {
        Runtime rt=Runtime.getRuntime();
        ArrayList<String> comando=new ArrayList<>(Arrays.asList("java","-cp",rutaJar,nombrePaquete));
        ArrayList<Integer> numerosIntroducidos=new ArrayList<>();

        Random random=new Random();
        int numRandom=random.nextInt(15)+1; //numero aleatorio entre 1 e 15(fagoo asi pq a funcion saca de 0 a 14)
        System.out.println("Numero de argumentos: "+String.valueOf(numRandom));
        comando.add(String.valueOf(numRandom));
        for (int i=0;i<numRandom;i++){
            int num=random.nextInt(100)+1;
            numerosIntroducidos.add(num);
            comando.add(String.valueOf(num));
        }

        //convertimolo en String[]
        String[] comandoArray=comando.toArray(new String[0]); //ao poñer String[0] java genera un array co tamaño adecuado pa gardar o arraylist
        try{
            Process process=rt.exec(comandoArray);
            //salida do comando, feito con scanner
            Scanner escaner=new Scanner(process.getInputStream());
            int posicionNumeros=0;
            while(escaner.hasNextLine()){
                System.out.println(String.valueOf(numerosIntroducidos.get(posicionNumeros++)) +" - "+ escaner.nextLine());
            }

            int exitStatus=process.waitFor();

            if (exitStatus==0){
                System.out.println("Todo correcto");
            }else {
                System.out.println("Ocorreu un erro: "+String.valueOf(exitStatus));
                Scanner escanerErros=new Scanner(process.getErrorStream());
                while (escanerErros.hasNextLine()){
                    System.out.println(escanerErros.nextLine());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
