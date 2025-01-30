package ejer6GuessANumberMultiThread.Client;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Client {
    private static final Path CONFIGURATION_FILE = Paths.get("src/main/java/ejer6GuessANumberMultiThread/config.properties");
    private static final int PORT= 60000;
    private static final String HOST_NAME = "localhost";

    public static void main(String[] args) {
        Properties properties = loadProperties();
        System.setProperty("javax.net.ssl.trustStore",properties.getProperty("publicKeyFilePath"));
        System.setProperty("javax.net.ssl.trustStorePassword",properties.getProperty("publicKeyPassword"));

        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try(
                SSLSocket sslSocket = (SSLSocket) socketFactory.createSocket(HOST_NAME,PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()))
                ){
            /* mirar ben como facer o tema de que se quede escuitando o reader e que poda escribir cando faga falta */
            System.out.println(reader.readLine());
        }catch (IOException e){
            System.out.println(e.getMessage());
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
