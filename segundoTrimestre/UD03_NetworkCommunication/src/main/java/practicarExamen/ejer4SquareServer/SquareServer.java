package practicarExamen.ejer4SquareServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SquareServer {
    private static final int SERVER_PORT = 50000;
    private static final int NUMBER_OF_CLIENTS_CONNECTED = 10;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static AtomicInteger numOfPetitions;

    public static void main(String[] args) {
        numOfPetitions = new AtomicInteger(0);

        try(
                ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
                ServerSocket serverSocket = new ServerSocket(SERVER_PORT,NUMBER_OF_CLIENTS_CONNECTED);
        ){
            System.out.println("Servidor escuchando por peticiones...");
            while (true){
                Socket petitionSocket = serverSocket.accept();
                threadPool.execute(new SquareServerWorker(petitionSocket));
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
