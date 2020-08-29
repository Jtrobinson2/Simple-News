package com.example.simplenews.repositories;

import com.example.simplenews.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * This interface contains the methods we are
 * going to use to execute the HTTP request with retrofit2
 * default page size I'm returning from the NEws api is 100 pages
 * */

public interface NewsAPI {
    /*TODO insert your api key into this variable here to use the app*/
    public static final String API_KEY = "";

    //  Get general news headlines from news API
    @GET("top-headlines?country=us&pageSize=100&apiKey=" + API_KEY)
    Call<NewsResponse> getGeneralNewsHeadlines();

    /*
     * Get topic specific headlines from news API
     * @param: String of one of the 7 available topics
     * You can find the topics in the NewsRepository.java they are static string constants
     * */
    @GET("top-headlines?country=us&pageSize=100&apiKey=" + API_KEY)
    Call<NewsResponse> getTopicSpecificHeadline(@Query("category") String category);


}
