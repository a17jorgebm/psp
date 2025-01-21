package jsonReader;

import Ejer2_DownloadWeb.Article;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;

public class ArticleTypeAdapter extends TypeAdapter<Article> {
    @Override
    public void write(JsonWriter jsonWriter, Article article) throws IOException {

    }

    @Override
    public Article read(JsonReader jsonReader) throws IOException {
        Article article = new Article();
        while (jsonReader.hasNext()){
            switch (jsonReader.nextName()){
                case "success":
                    if (!jsonReader.nextBoolean()) return null;
                    break;
                case "blog":
                    article = readArticle(jsonReader);
            }
        }
    }

    private Article readArticle(JsonReader jsonReader) throws IOException{
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            switch (jsonReader.nextName()){

            }
        }
        jsonReader.endObject();
        return null;
    }

    private LocalDateTime readDate(){

    }
}
