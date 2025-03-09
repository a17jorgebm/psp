package practicarExamen.ejer6GuessANumber;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable{
    private static final String END_MARK="%%END%%";

    private static final HashMap<Integer, String> RESPONSE_CODES = new HashMap<>(Map.ofEntries(
            Map.entry(10, "10 Number game server ready"),
            Map.entry(11, "11 BYE"),
            Map.entry(15, "15 Number game server ready"),
            Map.entry(20, "20 PLAY %d"),
            Map.entry(25, "25 LOW %d"),
            Map.entry(35, "35 HIGH %d"),
            Map.entry(40, "40 INFO"),
            Map.entry(50, "50 WIN"),
            Map.entry(70, "70 LOSE NUM %d"),
            Map.entry(80, "80 ERR"),
            Map.entry(90, "90 UNKNOWN")
    ));

    private Game game;
    private final Socket requestSocket;

    public ClientHandler(Socket requestSocket) {
        this.requestSocket = requestSocket;
    }

    @Override
    public void run() {
        try(
                PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(requestSocket.getOutputStream()),true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()))
                ){
            writeResponse(socketOutput, RESPONSE_CODES.get(10)); //servidor listo
            loop: while (true){
                String separatedCommand[] = getSeparatedCommand(readResponse(bufferedReader));
                Command comand = Command.getCommandFromText(separatedCommand[0]);
                switch (comand){
                    case NEW -> {
                        try{
                            int numberOfLifes = Integer.parseInt(separatedCommand[1]);
                            game = new Game(numberOfLifes);
                            writeResponse(socketOutput, String.format(RESPONSE_CODES.get(20),numberOfLifes));
                        }catch (NumberFormatException e){
                            writeResponse(socketOutput, RESPONSE_CODES.get(80)); //non manda ben o numero de vidas
                        }
                    }
                    case NUM -> {
                        //se non esta o xogo creado non pode adivinar
                        if (game==null){
                            writeResponse(socketOutput,RESPONSE_CODES.get(80));
                            continue;
                        }

                        //comprobo o numero q metiu
                        int guessNumber;
                        try {
                            guessNumber = Integer.parseInt(separatedCommand[1]);
                        }catch (NumberFormatException e){
                            writeResponse(socketOutput, RESPONSE_CODES.get(80)); //non manda ben o numero de vidas
                            continue;
                        }

                        //resultado
                        int guessResult = game.guessNumber(guessNumber);
                        if (game.isGameEnded()){ //se o xogo acabou
                            if (game.isUserWon()){
                                writeResponse(socketOutput, RESPONSE_CODES.get(50));
                            }else {
                                writeResponse(socketOutput, String.format(RESPONSE_CODES.get(70),game.getNumberToGuess()));
                            }
                            game = null;
                        }else {
                            if (guessResult==-1){
                                writeResponse(socketOutput, String.format(RESPONSE_CODES.get(35),guessNumber)); //maior que o correcto
                            }else{
                                writeResponse(socketOutput, String.format(RESPONSE_CODES.get(25),guessNumber)); //menor que o correcto
                            }
                        }
                    }
                    case HELP -> {
                        writeResponse(socketOutput,Command.getCommandHelpText());
                    }
                    case QUIT -> {
                        writeResponse(socketOutput, RESPONSE_CODES.get(11));
                        break loop;
                    }
                    case null, default -> writeResponse(socketOutput,RESPONSE_CODES.get(90));
                }
            }
            requestSocket.close();
        }catch (IOException e){
            System.out.println("Error in thread "+Thread.currentThread().getName()+":" +e.getMessage());
        }
    }

    private void writeResponse(PrintWriter writer, String text) throws IOException{
        writer.println(text+END_MARK);
    }

    private String readResponse(BufferedReader reader) throws IOException{
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

    private static String[] getSeparatedCommand(String command){
        return command.trim().split("\\s+");
    }
}
