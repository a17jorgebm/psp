package Ejer2_DownloadWeb.jsonReader;

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

        jsonReader.beginObject();
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
        jsonReader.endObject();
        return article;
    }

    private Article readArticle(JsonReader jsonReader) throws IOException{
        Article article=new Article();
        jsonReader.beginObject();
        while (jsonReader.hasNext()){
            switch (jsonReader.nextName()){
                case "content_text" -> article.setContentText(jsonReader.nextString());
                case "user_id" -> article.setUserId(jsonReader.nextInt());
                case "title" -> article.setTitle(jsonReader.nextString());
                case "photo_url" -> article.setPhotoUrl(jsonReader.nextString());
                case "created_at" -> article.setCreatedAt(readDate(jsonReader));
                case "id" -> article.setId(jsonReader.nextInt());
                case "description" -> article.setDescription(jsonReader.nextString());
                case "content_html" -> article.setContentHtml(jsonReader.nextString());
                case "category" -> article.setCategory(jsonReader.nextString());
                case "updated_at" -> article.setUpdatedAt(readDate(jsonReader));
                default -> jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return article;
    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    private LocalDateTime readDate(JsonReader jsonReader) throws IOException{
        String data = jsonReader.nextString().replace('T',' ');
        return LocalDateTime.parse(data,dateTimeFormatter);
    }
}
