package Ejer2_DownloadWeb;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;

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

        try(
                BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringWriter stringWriter = new StringWriter();
                ){
            String line;
            while((line= bufferedInputStream.readLine())!=null){
                stringWriter.write(line);
            }
        }catch (IOException e){

        }


    }
}