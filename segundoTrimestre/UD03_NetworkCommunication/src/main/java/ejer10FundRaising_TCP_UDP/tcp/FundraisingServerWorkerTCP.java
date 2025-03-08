package ejer10FundRaising_TCP_UDP.tcp;

import javax.net.ssl.SSLSocket;
import java.io.*;

public class FundraisingServerWorkerTCP implements Runnable{
    private static final String INVALID_MESSAGE = "Invalid request\n";

    private SSLSocket clientConnection;
    private Funds funds;

    public FundraisingServerWorkerTCP(SSLSocket clientConnection, Funds funds) {
        this.clientConnection = clientConnection;
        this.funds = funds;
    }

    @Override
    public void run() {
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream()));
                ){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                line = line.toUpperCase().trim();
                String[] separatedLine = line.split(" ");
                String command = separatedLine[0];

                switch (command){
                    case "ADD":

                        break;
                    case "SHOW":

                        break;
                    case "QUIT":

                        break;
                    default:
                        bufferedWriter.write(INVALID_MESSAGE);
                        bufferedWriter.flush();

                        System.out.println(String.format("Invalid request from %s",clientConnection.getInetAddress()));
                        break;
                }
            }
        }catch (IOException e){
            System.out.println("Error in petition");
        }
    }
}
