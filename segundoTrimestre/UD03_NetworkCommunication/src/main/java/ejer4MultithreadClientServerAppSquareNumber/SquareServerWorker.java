package ejer4MultithreadClientServerAppSquareNumber;

import java.io.*;
import java.net.Socket;

public class SquareServerWorker implements Runnable{
    private final Socket socket;

    public SquareServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ){
            String line;
            while ((line = bufferedReader.readLine())!=null){
                try{
                    Integer num = Integer.valueOf(line);
                    bufferedWriter.write((num*num)+"\n");
                    bufferedWriter.flush();
                    System.out.println("Served number "+(num*num)+" to client with ip: "+ socket.getRemoteSocketAddress());
                }catch (NumberFormatException e){
                    bufferedWriter.write("Bad argument, only numbers"+"\n");
                    bufferedWriter.flush();
                    System.out.println("non é numero: "+line);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                System.out.println("Couldnt close connection...");
            }
        }
    }
}
