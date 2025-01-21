package jsonReader;

import Ejer2_DownloadWeb.Article;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        return null;
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

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSSS");

    private LocalDateTime readDate(JsonReader jsonReader) throws IOException{
        String data = jsonReader.nextString().replace('T',' ');
        return LocalDateTime.parse(data,dateTimeFormatter);
    }
}
