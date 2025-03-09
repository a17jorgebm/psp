package practicarExamen.ejer4SquareServer;

import java.io.*;
import java.net.Socket;

public class SquareServerWorker implements Runnable{
    Socket requestSocket;

    public SquareServerWorker(Socket requestSocket) {
        this.requestSocket = requestSocket;
    }

    @Override
    public void run() {
        try(
                PrintWriter outputPrint = new PrintWriter(new OutputStreamWriter(requestSocket.getOutputStream()),true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()))
                ){
            Integer number = Integer.valueOf(bufferedReader.readLine());
            System.out.println(number);
            if (number!=null){
                outputPrint.println(number*number);
            }else {
                outputPrint.println("Invalid argument");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
