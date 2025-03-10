package practicarExamen.ejer7RandomWord;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.URI;
import java.net.URLConnection;

public class RandomWordServerWorker implements Runnable{
    private SSLSocket requestSocket;

    public RandomWordServerWorker(SSLSocket requestSocket) {
        this.requestSocket = requestSocket;
    }

    @Override
    public void run() {
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(requestSocket.getOutputStream()),true);
                ){
            System.out.println(requestSocket.getPort());
            writeRequest(printWriter,"Server ready to give random word...");
            while (true){
                String[] splitedRequest = getSplitedCommand(readRequest(reader));
                Command command = Command.getCommandFromText(splitedRequest[0]);
                switch (command){
                    case WORD -> {
                        int wordLenght = 5;
                        if (splitedRequest.length>1){
                            try{
                                wordLenght = Integer.parseInt(splitedRequest[1]);
                            }catch (NumberFormatException e){
                                writeRequest(printWriter, "Invalid command parameter: "+splitedRequest[1]);
                            }
                        }
                        try{
                            writeRequest(printWriter, getRandomWord(wordLenght));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            writeRequest(printWriter,"Error trying to generate a random word... try again");
                        }
                    }
                    case null, default -> writeRequest(printWriter, "Ola que tal chavales, tamos tontitos?");
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static String getRandomWord(int lenght) throws Exception{
        URLConnection urlConnection = null;
        urlConnection = new URI("https://random-word-api.herokuapp.com/word?length="+lenght).toURL().openConnection();

        StringBuilder stringBuilder = new StringBuilder();
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                ){
            String line;
            while ((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.length()>2 ?  stringBuilder.substring(2, stringBuilder.length()-2) : "No words found";
    }

    private static String readRequest(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private static void writeRequest(PrintWriter printWriter, String texto){
        printWriter.println(texto);
    }

    private static String[] getSplitedCommand(String command){
        return command.trim().split("\\s+");
    }
}
