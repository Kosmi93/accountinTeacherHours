package ru.biponline.accountinteacherhours.http_client;

import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


//получаем строку json а в классе будем парсить
// получаем либо расписание, либо что-то по ключу

public class HTTPutils {
    private static final OkHttpClient client = new OkHttpClient();
    public String get(String url) throws IOException {
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        try (Response response = client
                .newCall(request)
                .execute()){
            return response.body() != null? response.body().string():"";
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return ""; //исправить
    }
}
