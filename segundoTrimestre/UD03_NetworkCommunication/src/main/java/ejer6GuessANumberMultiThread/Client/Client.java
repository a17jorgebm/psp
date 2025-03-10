package ejer6GuessANumberMultiThread.Client;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    private static final Path CONFIGURATION_FILE = Paths.get("src/main/java/ejer6GuessANumberMultiThread/server.config.properties");
    private static final int PORT= 60000;
    private static final String HOST_NAME = "localhost";

    public static void main(String[] args) {
        Properties properties = loadProperties();
        System.setProperty("javax.net.ssl.trustStore",properties.getProperty("publicKeyFilePath"));
        System.setProperty("javax.net.ssl.trustStorePassword",properties.getProperty("publicKeyPassword"));

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try(
                SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOST_NAME,PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
                Scanner userInput = new Scanner(System.in);
                ){
            while(true){

            }
        }catch (IOException e){

        }
    }

    private String readServerMessage(BufferedReader server){
        return null;
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
