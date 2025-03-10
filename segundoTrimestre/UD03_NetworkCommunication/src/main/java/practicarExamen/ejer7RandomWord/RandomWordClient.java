package practicarExamen.ejer7RandomWord;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class RandomWordClient {
    private static final String HOST = "localhost";
    private static final int PORT = 50000;
    public static void main(String[] args) {
        try{
            readProperties().forEach((k,v)->System.setProperty(k.toString(),v.toString()));
        }catch (IOException e){
            System.out.println("Error trying to read server properties: "+e.getMessage());
        }

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try(
                SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOST,PORT);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()),true);
                Scanner scanner = new Scanner(System.in)
                ){
            while (true){
                System.out.println(readRequest(bufferedReader));
                System.out.print("> ");
                writeRequest(printWriter, scanner.nextLine());
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String readRequest(BufferedReader bufferedReader) throws IOException{
        return bufferedReader.readLine();
    }

    private static void writeRequest(PrintWriter printWriter, String message) throws IOException{
        printWriter.println(message);
    }

    private static Properties readProperties() throws IOException{
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/practicarExamen/ejer7RandomWord/configFiles/client.config.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
    }
}
