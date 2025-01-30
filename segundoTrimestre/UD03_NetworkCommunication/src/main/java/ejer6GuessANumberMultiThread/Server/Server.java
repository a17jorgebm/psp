package ejer6GuessANumberMultiThread.Server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final Path CONFIGURATION_FILE = Paths.get("src/main/java/ejer6GuessANumberMultiThread/config.properties");
    private static final int LISTEN_PORT = 60000;
    private static final int MAX_PARALLEL_CLIENTS = 100;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    private static final String HELP_TEXT =
            "NEW [number_of_attempts]: This command indicates that the client wants to start a new game. " +
            "As an argument it accepts the number of tries the user wants to have to guess the number. Example: NEW 8\n" +
            "NUM [number_to_try]: The client sends its guess to the server. A new game has to be created before using this command. Example: NUM 42.\n" +
            "HELP: The client asks the server for information about the game and the commands to use.\n" +
            "QUIT: The client sends the request to terminate the communication with the server.";

    public static void main(String[] args) {
        //load the properties
        Properties properties = loadProperties();
        System.setProperty("javax.net.ssl.keyStore",properties.getProperty("privateKeyFilePath"));
        System.setProperty("javax.net.ssl.keyStorePassword",properties.getProperty("privateKeyPassword"));

        //initiate the server
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try(
                SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(LISTEN_PORT,MAX_PARALLEL_CLIENTS);
                ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        ){
            System.out.println("Server initiaded, listening to petitions in port "+LISTEN_PORT+" working with "+NUMBER_OF_THREADS+" threads. Max number of parallel clients: "+MAX_PARALLEL_CLIENTS);
            //listen to petitions, new thread in the threadPool for each one
            while (true){
                try{
                    SSLSocket clientPetition = (SSLSocket) sslServerSocket.accept();
                    threadPool.execute(new ClientPetitionManager(clientPetition));
                }catch (IOException e){
                    System.out.println("Error in petition");
                }
            }
        }catch (IOException e){
            System.out.println("Error initiating the server: "+e.getMessage());
        }
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(CONFIGURATION_FILE.toFile())){
            properties.load(fileInputStream);
            return properties;
        }catch (IOException e){
            System.out.println("No ha sido posible cargar el fichero de configuración: "+ e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
