package practicarExamen.ejer6ConSSLGuessANumber;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    private static final String END_MARK="%%END%%";

    private static final int PORT = 50000;
    private static final String HOST_IP = "localhost";

    public static void main(String[] args) {
        try{
            readProperties().forEach((k,v)->System.setProperty(k.toString(),v.toString()));
        }catch (IOException e){
            System.out.println("Cant load properties: "+e.getMessage());
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try(
                SSLSocket socket = (SSLSocket) sslSocketFactory.createSocket(HOST_IP,PORT);
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ){
            while (true){
                System.out.println(readResponse(bufferedReader));
                System.out.print("> ");
                writeResponse(printWriter,scanner.nextLine());
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void writeResponse(PrintWriter writer, String text) throws IOException{
        writer.println(text+END_MARK);
    }

    private static String readResponse(BufferedReader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line=reader.readLine())!=null){
            line = line.trim();
            int endMarkPosition = line.indexOf(END_MARK);
            if (endMarkPosition!=-1){ //se ten marca de fin añadimos o texto ata ahí e acabamos o bucle
                stringBuilder.append(line, 0, endMarkPosition);
                break;
            }else {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    private static Properties readProperties() throws IOException{
        try(FileInputStream fileInputStream = new FileInputStream(Path.of("src/main/java/practicarExamen/ejer6ConSSLGuessANumber/client.config.properties").toFile())){
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties;
        }
    }
}
