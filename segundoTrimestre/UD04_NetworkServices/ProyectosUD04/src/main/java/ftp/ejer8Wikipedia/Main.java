package ftp.ejer8Wikipedia;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    private static final String HOST_NAME = "192.168.56.1";
    private static final int HOST_PORT = 21;
    private static final String USER_NAME = "user";
    private static final String USER_PASSWORD = "user";

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try{
            ftpClient.connect(HOST_NAME,HOST_PORT);
            readServerResponses(ftpClient);
            int responseCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(responseCode)) showErrorAndExit("Error ao conectar co servidor ftp: "+responseCode);

            boolean isLogged = ftpClient.login(USER_NAME,USER_PASSWORD);
            readServerResponses(ftpClient);
            if (!isLogged) showErrorAndExit("Couldnt login...");

            listDirectory(ftpClient);

            //subir ficheiro
            try(FileInputStream fileInputStream = new FileInputStream("./pom.xml");
                FileOutputStream fileOutputStream = new FileOutputStream("./src/main/java/ftp/ejer8Wikipedia/file.xml");
            ){
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.storeFile("pom.xml",fileInputStream);
                ftpClient.retrieveFile("pom.xml",fileOutputStream);
                readServerResponses(ftpClient);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static void readServerResponses(FTPClient ftpClient){
        String[] responses = ftpClient.getReplyStrings();

        if (responses!=null){
            for (String response : responses){
                System.out.println("SERVER: "+response);
            }
        }
    }

    private static void listDirectory(FTPClient ftpClient) throws IOException{
        System.out.println(ftpClient.printWorkingDirectory());
        readServerResponses(ftpClient);
        FTPFile[] ftpFiles = ftpClient.listFiles();
        readServerResponses(ftpClient);
        for (FTPFile file : ftpFiles){
            System.out.println(file.getName() + " - " + (file.isFile() ? "file" : "directory"));
        }
    }

    private static void showErrorAndExit(String error){
        System.out.println(error);
        System.exit(1);
    }
}
