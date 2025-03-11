package ftp.ejer10FTPorg;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    private static final String HOST = "ftp.scene.org";
    private static final int PORT = 21;
    private static final String USER = "anonymous";
    private static final String PASSWORD = "a17jorgebm@iessanclemente.net";

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(HOST,PORT);
            getReply(ftpClient);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                System.out.println("Fallo: "+ftpClient.getReply());
                System.exit(1);
            }
            boolean isLogged = ftpClient.login(USER,PASSWORD);
            getReply(ftpClient);
            if (!isLogged){
                System.out.println("Fallo no login");
                System.exit(1);
            }

            ftpClient.enterLocalPassiveMode();
            getReply(ftpClient);
            ftpClient.changeWorkingDirectory("pub");
            System.out.println(ftpClient.printWorkingDirectory());
            FTPFile[] files = ftpClient.listFiles();
            getReply(ftpClient);
            for (FTPFile file : files){
                System.out.println(file.getName() +" - "+ (file.isFile() ? "file" : "directory"));
            }

            try(FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/ftp/ejer10FTPorg/file.txt")){
                ftpClient.retrieveFile("uploading.txt",fileOutputStream);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void getReply(FTPClient ftpClient){
        String[] messages = ftpClient.getReplyStrings();
        if (messages!=null){
            for (String message : messages){
                System.out.println(message);
            }
        }
    }
}
