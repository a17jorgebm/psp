package ejer6GuessANumberMultiThread.Server;

import javax.net.ssl.SSLSocket;
import java.io.*;

public class ClientPetitionManager implements Runnable{
    private SSLSocket clientPetition;
    private Game currentGame;

    public ClientPetitionManager(SSLSocket clientPetition) {
        this.clientPetition = clientPetition;
    }

    @Override
    public void run() {
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientPetition.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientPetition.getOutputStream()))
                ){
            bufferedWriter.write("Ola que tal\n");
            bufferedWriter.flush();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
