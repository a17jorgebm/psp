package jsonReader;

import Ejer2_DownloadWeb.Article;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ArticleTypeAdapter extends TypeAdapter<Article> {
    @Override
    public void write(JsonWriter jsonWriter, Article article) throws IOException {

    }

    @Override
    public Article read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
