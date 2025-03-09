package practicarExamen.ejer2DownloadWebInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;

/**
 * The URL https://www.slingacademy.com/article/sample-blog-posts-public-rest-api-for-practice/ corresponds to a website that has a REST API webservice to get information about  fake blog posts.
 *
 * Write a program that asks the user for the id or the blog article and creates a webpage named article<id>.html  with the information returned by the web service.
 *
 * The following image shows the page created with the first article.
 */
public class Main {
    private static final String URL_DIRECTION = "https://api.slingacademy.com/v1/sample-data/blog-posts/1";
    public static void main(String[] args) {
        //chorrada de ejercicio, é pa practicar o da url asi que non me vou matar facendo o resto que non ten nada q ver
        URLConnection urlConnection = null;
        try{
            urlConnection = new URI(URL_DIRECTION).toURL().openConnection();
        }catch (URISyntaxException | MalformedURLException e){
            System.out.println("La url tiene un mal formato");
        }catch (IOException e){
            System.out.println("No se ha podido establecer conexion con la URL");
        }

        try(
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                ){
            String line;
            while ((line=bufferedReader.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            System.out.println("Error lendo o json");
        }
    }
}
