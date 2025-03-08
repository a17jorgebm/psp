package ejer10FundRaising_TCP_UDP.tcp;

import ejer6GuessANumberMultiThread.Server.ClientPetitionManager;

import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DU3 - Exercise 10 - Multi threaded client-server app - UDP - TCP - Funds raising
 *
 * We want to raise funds using a client-server application.
 *
 * The client must send to the server a command entered by the user. The only correct commands are:
 *
 * ADD <amount>: Adds the specified amount to the funds.
 * SHOW: Returns the current fundraising total.
 * QUIT: Ends the communication with the server.
 * If the user does not specify the amount, or if the user does not use correctly the command, the server sends an Invalid request message to the client.
 *
 * The server must display information about all requests it receives.
 *
 * The server must also display information about all information it sends to clients, including the client's port. When the client adds an amount to the funds, the server must display the new amount.
 *
 * UDP:
 *
 * Create a FundraisingClientUDP class to launch the Client.
 *
 * Create a FundraisingServerUDP class to launch the Server.
 *
 * Create a FundraisingServerUDPWorker class for each thread on the Server.
 *
 * TCP:
 *
 * Create a FundraisingClientTCP class to launch the Client.
 *
 * Create a FundraisingServerTCP class to launch the Server.
 *
 * Create a FundraisingServerTCPWorker class for each thread on the Server.
 */

public class FundraisingServerTCP {
    private static final Path CONFIG_FILE = Path.of("src/main/java/config.properties");
    private static final int LISTENING_PORT = 61000;
    private static final int MAX_PARARELL_CLIENTS = 100;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        //load properties
        Properties properties = loadProperties();
        System.setProperty("javax.net.ssl.KeyStore",properties.getProperty("privateKeyFilePath"));
        System.setProperty("javax.net.ssl.KeyStorePassword",properties.getProperty("privateKeyPassword"));

        Funds funds = new Funds();

        //initiate the server
        SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try(
                SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(LISTENING_PORT,MAX_PARARELL_CLIENTS);
                ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        ){
            //server listening
            System.out.println(String.format("Server initiated, listening on port %d with %d threads. Max number of pararell clients: %d",LISTENING_PORT,NUMBER_OF_THREADS,MAX_PARARELL_CLIENTS));
            while (true){
                try{
                    SSLSocket socket =(SSLSocket) serverSocket.accept();
                    FundraisingServerWorkerTCP fundraisingServerWorkerTCP = new FundraisingServerWorkerTCP(socket, funds);
                    threadPool.execute(fundraisingServerWorkerTCP);
                }catch (IOException e){
                    System.out.println("Error trying to establish a client connection");
                }
            }
        }catch (IOException e){
            System.out.println("Error al iniciar el servidor: "+e.getMessage());
        }
    }

    private static Properties loadProperties(){
        Properties properties=new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE.toFile())){
            properties.load(fileInputStream);
            return properties;
        }catch (IOException e){
            System.out.println("Error trying to load the configuration file: "+e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
