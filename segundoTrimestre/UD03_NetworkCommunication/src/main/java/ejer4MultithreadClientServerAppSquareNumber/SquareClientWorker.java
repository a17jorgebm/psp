package ejer4MultithreadClientServerAppSquareNumber;

import java.io.*;
import java.net.Socket;

public class SquareClientWorker implements Runnable{
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 50001;

    private final String value;

    public SquareClientWorker(String value) {
        this.value = value;
    }

    @Override
    public void run() {
        try(
                Socket con = new Socket(SERVER_NAME,SERVER_PORT);
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))
                ){
            System.out.println("asking for value : "+value);

            writer.write(value+"\n");
            writer.flush();

            String messageReturned = reader.readLine();

            System.out.println("For value "+value+" the server returned: " + messageReturned);
        }catch (IOException e){
            System.out.println("Error in thread for value "+value+": "+e.getMessage());
        }
    }
}
