package Ejer2_DownloadWeb;

import Ejer2_DownloadWeb.jsonReader.ArticleTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * DU3 - Exercise 2 - Download information from a URLs and create web pages
 *
 * The URL https://www.slingacademy.com/article/sample-blog-posts-public-rest-api-for-practice/ corresponds to a
 * website that has a REST API webservice to get information about  fake blog posts.
 *
 * Write a program that asks the user for the id or the blog article and creates a webpage named article<id>.html
 * with the information returned by the web service.
 *
 * The following image shows the page created with the first article.
 */

public class Main {
    private static final String API_URL="https://api.slingacademy.com/v1/sample-data/blog-posts/%[id]";
    private static final Path HTML_TEMPLATE = Path.of("./src/main/java/Ejer2_DownloadWeb/page/index.html");
    private static final Path HTML_FINAL = Path.of("./src/main/java/Ejer2_DownloadWeb/page/index_final.html");

    public static void main(String[] args) {
        System.out.print("Insert the id of the blog to download: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()){
            System.out.println("Bad input... say a number next time");
            System.exit(1);
        }
        String id=scanner.nextLine();
        String url=API_URL.replace("%[id]",id);

        URLConnection urlConnection=null;
        try{
            urlConnection=new URI(url).toURL().openConnection();


        }catch (Exception e){

        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Article.class, new ArticleTypeAdapter())
                .create();

        try(
                BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                ){
            Article article = gson.fromJson(bufferedInputStream, Article.class);

            HashMap<String, String> placeholders = new HashMap<>(Map.ofEntries(
                    Map.entry("%%TITLE%%",article.getTitle()),
                    Map.entry("%%BODY%%", article.getContentHtml()),
                    Map.entry("%%CREATED_AT%%",article.getCreatedAt().toString()),
                    Map.entry("%%IMAGE_SRC%%",article.getPhotoUrl()),
                    Map.entry("%%USER_NAME%%","Na")
            ));

            String htmlTemplate = Files.readString(HTML_TEMPLATE);
            Files.writeString(
                    HTML_FINAL,
                    replacePlaceHolders(htmlTemplate,placeholders),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
        }catch (IOException e){

        }


    }

    private static String replacePlaceHolders(String htmlTemplate, HashMap<String,String> placeholders){
        StringBuilder resultado = new StringBuilder(htmlTemplate);
        placeholders.forEach((placeholder, value) -> {
            int startIndex = resultado.indexOf(placeholder);
            while (startIndex!=-1){
                resultado.replace(startIndex, startIndex+placeholder.length(), value);
                startIndex = resultado.indexOf(placeholder, startIndex+value.length());
            }
        });
        return resultado.toString();
    }
}