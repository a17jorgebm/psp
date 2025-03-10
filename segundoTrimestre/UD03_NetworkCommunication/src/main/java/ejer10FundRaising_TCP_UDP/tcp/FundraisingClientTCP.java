package ejer10FundRaising_TCP_UDP.tcp;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

public class FundraisingClientTCP {
    private static final Path CONFIG_FILE = Path.of("src/main/java/server.config.properties");
    private static final String HOST_NAME = "localhost";
    private static final int PETITION_PORT = 61000;

    public static void main(String[] args) {
        Properties properties = loadProperties();
        System.setProperty("javax.net.ssl.trustStore",properties.getProperty("publicKeyFilePath"));
        System.setProperty("javax.net.ssl.trustStorePassword",properties.getProperty("publicKeyPassword"));

        SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try(
                SSLSocket socket = (SSLSocket) socketFactory.createSocket(HOST_NAME,PETITION_PORT);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ){
            bufferedWriter.write("OLA\n");
            bufferedWriter.flush();

            System.out.println(bufferedReader.readLine());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    private static Properties loadProperties(){
        Properties properties=new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE.toFile())){
            properties.load(fileInputStream);
            return properties;
        }catch (IOException e){
            System.out.println("Error importing the configuration: "+e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
