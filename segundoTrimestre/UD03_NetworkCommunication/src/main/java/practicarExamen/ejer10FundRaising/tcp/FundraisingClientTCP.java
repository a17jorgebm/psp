package practicarExamen.ejer10FundRaising.tcp;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class FundraisingClientTCP {
    private static final String HOST = "localhost";
    private static final int PORT = 50000;

    public static void main(String[] args) {
        //load the properties to de JVM
        try{
            readProperties().forEach((k,v)->System.setProperty(k.toString(),v.toString()));
        }catch (IOException e){
            System.out.println("Error trying to load server properties: "+e.getMessage());
        }

        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try(
                SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(HOST,PORT);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                PrintWriter printWriter =  new PrintWriter(new OutputStreamWriter(sslSocket.getOutputStream()),true);
                Scanner scanner = new Scanner(System.in);
                ){
            while (true){
                String response = readRequest(bufferedReader);
                if (response == null){
                    System.out.println("Server ended connection...");
                    break;
                }
                System.out.println(response);
                System.out.print("> ");
                writeRequest(printWriter,scanner.nextLine());
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void writeRequest(PrintWriter printWriter, String text){
        printWriter.println(text);
    }

    private static String readRequest(BufferedReader reader) throws IOException{
        return reader.readLine();
    }

    private static Properties readProperties() throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/practicarExamen/ejer10FundRaising/tcp/properties/client.config.properties")){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
    }
}
