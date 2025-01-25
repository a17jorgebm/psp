package ejer3SingleThreadedClientServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/***
 *

 DU3 - Exercise 3 - Single threaded client-server app - Polybius cipher

 We want to create a client-server application to encrypt messages using the polybius cipher.

 The client must send a message to be encrypted to the server.

 The server should encrypt the message and return the encrypted message to the client.

 To encrypt using the polybius cipher use the following table:

 polybius cypher

 Example:

 Message: proba

 Encrypted message: 34 36 33 12 11

 */

public class Server {
    private static final Map<Character, String> polybiusMap = new HashMap<>(){{
        // Row 1
        put('A', "11"); put('B', "12"); put('C', "13");
        put('D', "14"); put('E', "15"); put('F', "16");

        // Row 2
        put('G', "21"); put('H', "22"); put('I', "23");
        put('J', "24"); put('K', "25"); put('L', "26");

        // Row 3
        put('M', "31"); put('N', "32"); put('O', "33");
        put('P', "34"); put('Q', "35"); put('R', "36");

        // Row 4
        put('S', "41"); put('T', "42"); put('U', "43");
        put('V', "44"); put('W', "45"); put('X', "46");

        // Row 5
        put('Y', "51"); put('Z', "52"); put('0', "53");
        put('1', "54"); put('2', "55"); put('3', "56");

        // Row 6
        put('4', "61"); put('5', "62"); put('6', "63");
        put('7', "64"); put('8', "65"); put('9', "66");
    }};

    public static void main(String[] args) {
        try(
                ServerSocket serverSocket = new ServerSocket(50001, 1);
                Socket socket = serverSocket.accept();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                bufferedWriter.write(encryptMessage(line) + "\n");
                //se non facemos o flush dentro non se enviara o mensaje ao cliente, e polo tanto nunca se saldrá do bucle,
                //  xa que o readLine fará un wait() esperando a recibir unha liña do cliente, e esta solo será nula se o CLIENTE pecha a conexion
                    // 1. cerre a conexion
                    // 2. mande un mensaje vacio con salto de liña
                bufferedWriter.flush();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String encryptMessage(String message){
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<message.length();i++){
            char c = message.charAt(i);
            String encryptedMessage = polybiusMap.get(Character.toUpperCase(c));
            stringBuilder.append(encryptedMessage!=null ? encryptedMessage+" " : " ");
        }
        return stringBuilder.toString();
    }
}
