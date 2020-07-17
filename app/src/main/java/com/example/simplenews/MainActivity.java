package com.example.simplenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.simplenews.adapters.NewsArticleAdapter;
import com.example.simplenews.models.Article;
import com.example.simplenews.models.Example;
import com.example.simplenews.repositories.NewsAPI;
import com.example.simplenews.repositories.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView newsRecyclerView;
    private NewsArticleAdapter newsAdapter;
    private NewsAPI NewsAPI;
    private ArrayList<Article> newsArticles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsAdapter = new NewsArticleAdapter(new ArrayList<Article>(), this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        newsRecyclerView.setLayoutManager(layoutManager);
        newsRecyclerView.setAdapter(newsAdapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsAPI = retrofit.create(NewsAPI.class);

        Call<Example> call = NewsAPI.getRootJSONObject();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()) {
                        newsArticles = (ArrayList<Article>) response.body().getArticles();
                        refreshAdapterWithNewsArticles(newsArticles);
                }
                else {
                    Log.d("MainActivity", "Error in on Response " + String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error retrieving News Articles :(", Toast.LENGTH_SHORT).show();
            }
        });



    }
    /*
    *Method used to generate list of data using recyclerView with costom adapter
    *  */

    private void refreshAdapterWithNewsArticles(List<Article> body) {
        newsAdapter = null;
        newsAdapter = new NewsArticleAdapter(body, this);
        newsRecyclerView.setAdapter(newsAdapter);

    }
}