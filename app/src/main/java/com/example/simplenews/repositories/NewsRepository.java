package com.example.simplenews.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.simplenews.models.Article;
import com.example.simplenews.models.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;
    private NewsAPI newsAPI;

    public static NewsRepository getInstance() {
        if(newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    private NewsRepository() {
        newsAPI = RetrofitClient.getRetrofitInstance().create(NewsAPI.class);
    }

    public MutableLiveData<Example> getTopHeadlines() {
        MutableLiveData<Example> topHeadlines = new MutableLiveData<>();
        newsAPI.getRootJSONObject().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful()) {
                    topHeadlines.setValue( response.body());
                    Log.d("News Repository", "Network call was successful " + response.code());
                }
                else {
                    Log.d("News Repository", "Network call was unsuccessful" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("News Repository", "Network call failed ");
                topHeadlines.setValue(null);
            }
        });
        return topHeadlines;
    }
}
