package practicarExamen.ejer10FundRaising.tcp;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FundRaisingServerTCP {
    private static final int PORT = 50000;
    private static final int MAX_CLIENTS = 20;
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() > 1 ? Runtime.getRuntime().availableProcessors()/2 : 1;

    public static void main(String[] args) {
        //cargo propiedades
        try{
            readProperties().forEach((k,v)->System.setProperty(k.toString(),v.toString()));
        }catch (IOException e){
            System.out.println("Couldnt load server properties: "+e.getMessage());
            System.exit(1);
        }

        Fund fund = new Fund();
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try(
                ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);
                SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(PORT,MAX_CLIENTS);
                ){
            System.out.println(String.format("Server initiated in port %d, working with %d threads. Max number of clients: %d",PORT,MAX_THREADS,MAX_CLIENTS));
            while (true){
                SSLSocket socket = (SSLSocket) sslServerSocket.accept();
                threadPool.execute(new FundraisingServerTCPWorker(socket, fund));
            }
        }catch (IOException e){
            System.out.println("Couldnt initiate the server: "+e.getMessage());
        }
    }

    private static Properties readProperties() throws IOException{
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/practicarExamen/ejer10FundRaising/tcp/properties/server.config.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
    }
}
