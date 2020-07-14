package com.example.simplenews.repositories;

import com.example.simplenews.models.Article;

import retrofit2.Call;
import retrofit2.http.GET;

/*
* This interface contains the methods we are
* going to use to execute the HTTP request with retrofit
* */
public interface NewsAPI {
    @GET("/top-headlines?country=us&apiKey=a130b9d595a2430f9a0e31d7641bdef2")
    Call<Article> getArticles();

}
