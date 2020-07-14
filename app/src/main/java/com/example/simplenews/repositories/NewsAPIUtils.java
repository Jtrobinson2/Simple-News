package com.example.simplenews.repositories;

//Class for defining static base url for app and
// News API interface in our app
public class NewsAPIUtils {
    public static final String BASE_URL = "https://newsapi.org/v2/";

    public static NewsAPI getNewsAPI() {
        return RetrofitClient.getClient(BASE_URL).create(NewsAPI.class);
    }
}
