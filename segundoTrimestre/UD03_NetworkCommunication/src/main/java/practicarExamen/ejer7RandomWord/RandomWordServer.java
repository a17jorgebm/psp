package practicarExamen.ejer7RandomWord;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RandomWordServer {
    private static final int PORT = 50000;
    private static final int MAX_CLIENTS = 50;
    private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors() > 1 ? Runtime.getRuntime().availableProcessors()/2: 1;

    public static void main(String[] args) {
        //gardo as claves ssl
        try{
            getSSLProperties().forEach((k,v)->System.setProperty(k.toString(),v.toString()));
        }catch (IOException e){
            System.out.println("Cant load properties: "+e.getMessage());
        }

        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try(
                ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREADS);
                SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(PORT, MAX_CLIENTS);
                ){
            System.out.println(String.format("Server initiated in por %d, working with %d with a max load of %d clients",PORT,MAX_THREADS,MAX_CLIENTS));
            while (true){
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
                threadPool.execute(new RandomWordServerWorker(sslSocket));
            }
        }catch (IOException e){
            System.out.println("Error trying to initiate server: "+e.getMessage());
        }
    }

    private static Properties getSSLProperties() throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream(Path.of("src/main/java/practicarExamen/ejer7RandomWord/configFiles/server.config.properties").toFile())){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
    }
}
