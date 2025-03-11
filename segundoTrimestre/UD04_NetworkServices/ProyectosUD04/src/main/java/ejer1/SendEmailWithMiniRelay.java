package ejer1;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SendEmailWithMiniRelay {
    private static final Path CONFIGURATION_FILE = Paths.get("config.properties");

    public static void main(String[] args) {
        Properties properties = loadProperties();

        String sender = "basurerodebj@gmail.com";
        String receiver="alt.c4-6om1r70w@yopmail.com";
        Session session = Session.getInstance(properties);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender,"Jorge Blanco"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver,"Outro Jorge"));
            message.setSubject("Hello from Java");
            message.setText("Email sent from Java app and captured by Mailslurper.");
            Transport.send(message);
            System.out.println("Email sent.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending mail.");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties loadProperties(){
        Properties properties = new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(CONFIGURATION_FILE.toFile())){
            properties.load(fileInputStream);
            return properties;
        }catch (IOException e){
            System.out.println("No ha sido posible cargar el fichero de configuración: "+ e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
