package ejer6GuessANumberMultiThread.Server;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ClientPetitionManager implements Runnable{
    private static final String HELP_TEXT =
            "NEW [number_of_attempts]: This command indicates that the client wants to start a new game. " +
                "As an argument it accepts the number of tries the user wants to have to guess the number. Example: NEW 8\n" +
            "NUM [number_to_try]: The client sends its guess to the server. A new game has to be created before using this command. Example: NUM 42.\n" +
            "HELP: The client asks the server for information about the game and the commands to use.\n" +
            "QUIT: The client sends the request to terminate the communication with the server.";

    private static final Map<Integer, String> RESPONSE_CODE = new HashMap<>(Map.ofEntries(
            Map.entry(10, "10 Number game server ready\n"),
            Map.entry(11, "11 BYE\n"),
            Map.entry(15, "15 Number game server ready\n"),
            Map.entry(20, "20 PLAY <numOp>\n"),
            Map.entry(25, "25 LOW <numOp>\n"),
            Map.entry(35, "35 HIGH <numOp>\n"),
            Map.entry(40, "40 INFO\n"),
            Map.entry(50, "50 WIN\n"),
            Map.entry(70, "70 LOSE NUM <numGuess>\n"),
            Map.entry(80, "80 ERR\n"),
            Map.entry(90, "90 UNKNOWN\n"),
            Map.entry(91, "91 INVALID PARAMETERS")
    ));

    private SSLSocket clientPetition;
    private Game currentGame;

    public ClientPetitionManager(SSLSocket clientPetition) {
        this.clientPetition = clientPetition;
    }

    @Override
    public void run() {
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientPetition.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientPetition.getOutputStream()))
                ){
            System.out.println("ola");
            String line;
            while ((line=bufferedReader.readLine())!=null){
                String[] splitedCommand = line.trim().split(" ");
                String command = splitedCommand[0].toUpperCase();
                String argument = splitedCommand.length>1 ? splitedCommand[1] : null;
                System.out.println(command);
                switch (command){
                    case "NEW" :
                        if (argument==null){
                            bufferedWriter.write(RESPONSE_CODE.get(91));
                            bufferedWriter.flush();
                            break;
                        }

                        break;
                    case "NUM":

                        break;
                    case "HELP":
                        bufferedWriter.write(HELP_TEXT);
                        break;
                    case "QUIT":

                        break;
                    default:

                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
