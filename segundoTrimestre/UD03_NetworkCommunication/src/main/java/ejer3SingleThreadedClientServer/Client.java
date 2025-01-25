package ejer3SingleThreadedClientServer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try(
                Socket con = new Socket("localhost",50001);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))
                ){
            String mensaje = "Ola que tal mi gente 234234";
            System.out.println("Enviando mensaje: "+mensaje);

            bufferedWriter.write(mensaje+"\n");
            bufferedWriter.flush();

            //get the encrypted message
            String encryptedMessage = bufferedReader.readLine();
            System.out.println("Mensaje encriptado: "+ encryptedMessage);

        }catch (IOException e){

        }
    }
}
