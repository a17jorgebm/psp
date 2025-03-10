package practicarExamen.ejer10FundRaising.tcp;

import javax.net.ssl.SSLSocket;
import java.io.*;

public class FundraisingServerTCPWorker implements Runnable{
    private SSLSocket socket;
    private Fund fund;

    public FundraisingServerTCPWorker(SSLSocket socket, Fund fund) {
        this.socket = socket;
        this.fund = fund;
    }

    @Override
    public void run() {
        System.out.println(String.format(
                "Host %s connected from port %d, in local port %d",
                socket.getSession().getPeerHost(),
                socket.getPort(),
                socket.getLocalPort()
        ));
        try(
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ){
            writeRequest(printWriter,"Hi! You can donate anything you want");
            loop: while (true){
                String requestText = readRequest(bufferedReader);
                //realmente neste caso nunca entraría aqui, porque o cliente nunca cerra a conexion con close()
                if (requestText == null){
                    System.out.println("Client ended conection: "+socket.getSession().getPeerHost());
                }

                String[] splitedCommand = getSplitedCommand(requestText);
                Command command = Command.getCommandFromText(splitedCommand[0]);
                switch (command){
                    case ADD -> {
                        int amount;
                        if (splitedCommand.length>1){
                            try{
                                amount = Integer.parseInt(splitedCommand[1]);
                            }catch (NumberFormatException e){
                                writeRequest(printWriter,"Invalid request");
                                System.out.println("Invalid ADD request");
                                continue;
                            }
                        }else {
                            writeRequest(printWriter,"Invalid request");
                            System.out.println("Invalid ADD request");
                            continue;
                        }

                        writeRequest(printWriter, "Added "+amount+" to the fund. Thank you!");
                        System.out.println("Added "+amount+", new amount: "+ fund.addAndGetTotalAmount(amount));
                    }
                    case SHOW -> {
                        System.out.println("SHOW REQUEST");
                        writeRequest(printWriter, String.valueOf(fund.getTotalAmount()));
                    }
                    case QUIT -> {
                        System.out.println("QUIT by client: "+socket.getSession().getPeerHost());
                        socket.close();
                        break loop;
                    }
                    case null, default -> writeRequest(printWriter, "Invalid request");
                }
            }
        }catch (IOException e){
            System.out.println("Error in thread "+Thread.currentThread().getName()+": "+e.getMessage());
        }
    }

    private static String readRequest(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private static void writeRequest(PrintWriter printWriter, String text) throws IOException{
        printWriter.println(text);
    }

    private static String[] getSplitedCommand(String command){
        return command.trim().split("\\s+");
    }
}
