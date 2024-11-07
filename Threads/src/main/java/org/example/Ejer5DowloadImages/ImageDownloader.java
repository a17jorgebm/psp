package org.example.Ejer5DowloadImages;

import java.net.URI;
import java.net.URLConnection;
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
        System.out.println(savedImageName);
        System.out.println(url);
        /*
        URLConnection conexionURL=null;
        try{
            conexionURL=new URI(url).toURL().openConnection();
        }catch (Exception e){
            System.out.println("Error al conectarse con la url: "+e.getMessage());
        }

         */

    }
}
