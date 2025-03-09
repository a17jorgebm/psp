package practicarExamen.ejer3SingleThreadPolybusServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(
                Socket socket = new Socket(InetAddress.getByName("localhost").getHostAddress(), 50000);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ){
            bufferedWriter.write("proba\n");
            bufferedWriter.flush();
            String line;
            while ((line=bufferedReader.readLine()) != null){ //quedase esperando a un salto de liña, cerrase cando o servidor cerra conexion (line = null)
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
