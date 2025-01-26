package ejer4MultithreadClientServerAppSquareNumber;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * DU3 - Exercise 4 - Multi threaded client-server app - Square a number
 *
 * We want to create a client-server application for squaring integers.
 *
 * The client must send to the server a number entered by the user .
 *
 * The server must square the received number and send back to the client the obtained square  .
 *
 * The user can use the Client to send numbers to the server until they decide to exit the application. You can create a menu to handle this situation.
 *
 * Create a SquareClient class to launch the Client.
 *
 * Create a SquareServer class to launch the Server.
 *
 * Create a SquareClientWorker class for each thread on the Client.
 *
 * Create a SquareServerWorker class for each thread on the Server.
 */
public class SquareServer {
    private static final int PORT = 50001;
    private static final int NUMBER_OFF_CLIENTS_AT_A_TIME = 50; //creo que é o normal por defecto

    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        try(
                ServerSocket serverSocket = new ServerSocket(PORT, NUMBER_OFF_CLIENTS_AT_A_TIME);
                ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        ){
            System.out.println("Server listening on port "+PORT);
            while (true){
                try{
                    Socket socket = serverSocket.accept();
                    threadPool.execute(new SquareServerWorker(socket));
                }catch (IOException e){
                    System.out.println("Error accepting connection: "+e.getMessage());
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Error starting the server: "+e.getMessage());
        }
    }
}
