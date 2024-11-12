package org.example.Ejer5DowloadImages;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageDownloader implements Runnable{
    private static final Path IMAGE_SAVE_DIRECTORY=Path.of("src/main/java/org/example/Ejer5DowloadImages/resources");

    private String url;
    private int fileLine;
    private String savedImageName;

    public ImageDownloader(String url, int fileLine) {
        this.url = url;
        this.fileLine = fileLine;
        this.savedImageName="animal"+fileLine+".jpg";
    }

    @Override
    public void run() {
        try {
            Files.createDirectories(IMAGE_SAVE_DIRECTORY);
        }catch (IOException e){
            System.out.println("El directorio de guardado no existe y no se pudo crear: "+e.getMessage());
            System.exit(3);
        }

        Path newImagePath=IMAGE_SAVE_DIRECTORY.resolve(savedImageName);

        URLConnection conexionURL=null;
        try{
            conexionURL=new URI(url).toURL().openConnection();
        }catch (Exception e){
            System.out.println("Error al conectarse con la url, no se descargará la imagen: "+e.getMessage());
            return;
        }

        try(BufferedInputStream bi=new BufferedInputStream(conexionURL.getInputStream());
            BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(newImagePath.toFile()));
        ){
            int datos;
            while ((datos=bi.read())!=-1){
                outputStream.write(datos);
            }
        }catch (IOException e){
            System.out.printf("Error al guardar el fichero %s, de la url(%s). Error: %s%n",
                savedImageName,url,e.getMessage());
        }
    }
}
