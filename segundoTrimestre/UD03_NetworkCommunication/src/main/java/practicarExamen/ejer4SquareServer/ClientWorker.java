package practicarExamen.ejer4SquareServer;

import java.io.*;
import java.net.Socket;

public class ClientWorker implements Runnable{
    private static final String SERVER_NAME="localhost";
    private static final int SERVER_PORT = 50000;
    @Override
    public void run() {
        try(
                Socket petitionSocket = new Socket(SERVER_NAME,SERVER_PORT);
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(petitionSocket.getOutputStream()));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(petitionSocket.getInputStream()))
            ) {
            bufferedWriter.write("5\n");
            bufferedWriter.flush();

            String line;
            while ((line=bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Error en thread "+Thread.currentThread().getName()+": "+e.getMessage());
        }
    }
}
