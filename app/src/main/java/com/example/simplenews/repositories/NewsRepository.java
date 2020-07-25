package com.example.simplenews.repositories;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simplenews.MainActivity;
import com.example.simplenews.models.Article;
import com.example.simplenews.models.NewsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewsRepository {
    private static NewsRepository newsRepository;
    private NewsAPI newsAPI;




    private List<Article> freshArticles;


    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    /*
     * Private constructor because nobody should be creating this object direcly
     * */
    private NewsRepository() {
        newsAPI = RetrofitClient.getRetrofitInstance().create(NewsAPI.class);
    }

    public MutableLiveData<NewsResponse> getTopHeadlines() {
        MutableLiveData<NewsResponse> topHeadlines = new MutableLiveData<>();
        newsAPI.getRootJSONObject().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    topHeadlines.setValue(response.body());
                    Timber.d("Network call was succesful here is the response code " + response.code());
                } else {
                    Timber.d("Network call was unsuccesful " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Timber.d("Network call completely failed lol");
                topHeadlines.setValue(null);
            }
        });
        return topHeadlines;

    }



}
