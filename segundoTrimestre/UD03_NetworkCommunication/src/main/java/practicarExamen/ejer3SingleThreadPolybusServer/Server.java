package practicarExamen.ejer3SingleThreadPolybusServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
                ServerSocket serverSocket = new ServerSocket(50000, 10);
        ){
            System.out.println("Escuitando por peticions");
            while (true){
                try(
                        Socket petitionSocket = serverSocket.accept();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(petitionSocket.getInputStream()));
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(petitionSocket.getOutputStream()))
                ){
                    String line = bufferedReader.readLine();
                    bufferedWriter.write(encryptMessage(line));
                    bufferedWriter.write(" Ola que tal estadoes");
                    bufferedWriter.write("\n");
                    bufferedWriter.flush();
                }catch (IOException e){
                    System.out.println("Error trying to initiate a session");
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String encryptMessage(String message){
        message = message.toUpperCase();
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i=0;i<message.length();i++){
            String encryptedChar = polybiusMap.get(message.charAt(i));
            System.out.println(encryptedChar);
            if (encryptedChar!=null){
                encryptedMessage.append(encryptedChar);
            }
        }
        return encryptedMessage.toString();
    }
}
