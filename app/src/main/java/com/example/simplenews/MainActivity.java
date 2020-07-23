package com.example.simplenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.simplenews.adapters.NewsArticleAdapter;
import com.example.simplenews.adapters.RecyclerItemClickListener;
import com.example.simplenews.models.Article;
import com.example.simplenews.models.Example;
import com.example.simplenews.repositories.NewsAPI;
import com.example.simplenews.repositories.RetrofitClient;
import com.example.simplenews.viewmodels.NewsViewModel;
import com.google.gson.Gson;
import com.victor.loading.rotate.RotateLoading;

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
    private ArrayList<Article> newsArticles = new ArrayList<>();
    private RotateLoading rotateLoadingIndicator;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsViewModel newsViewModel;

    /*
     *  TODO: fix adapter to show articles published time in the cardview
     *   TODO: create pull to refresh feature on topHeadlines RecyclerView
     *   TODO: move the recycleview into a fragment
     *    TODO: Move netowrking code to repository
     *     TODO: setup live data, viewmodels, observable pattern*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        rotateLoadingIndicator = findViewById(R.id.rotate_loading_indicator);

//        Getting and setting up the viewmodel
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.initNewsViewModel();

//        Setting up the observer
        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            ArrayList<Article> freshNewsArticles = (ArrayList<Article>) newsResponse.getArticles();
            newsArticles.addAll(freshNewsArticles);
            newsAdapter.notifyDataSetChanged();
        });

        initReyclerView();


//        This is not the way to do recyclerview click listeners but this will suffice for now
        newsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, newsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Article article = newsArticles.get(position);
                        Uri uri = Uri.parse(article.getUrl());
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(webIntent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        // Configure the refreshing colors
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        /*
         * TODO: note that refreshing really doesn't change anything, because the datasource most likely isn't changing while the user
         *  is in the app. I have yet to see the recyclerview contents actually change when using pull to refresh
         *  yet when comparing the Arraylist response to the existing adapters data they aren't the same
         * */
        swipeRefreshLayout.setOnRefreshListener(() -> {


        });

    }


    /*
     * Helper method that refreshes topHeadlinesRecyclerView with new articles
     * @param: list of new article objects from a network request
     * */
    private void refreshNewsRecyclerView(List<Article> freshArticles) {
        newsAdapter.clearNewsArticles();
        newsAdapter.addAll(freshArticles);
    }

    /*
     * Helper method to show the loading indicator
     * */
    private void showLoadingIndicator() {
        rotateLoadingIndicator.setVisibility(View.VISIBLE);
        rotateLoadingIndicator.start();
    }

    /*
     * Helper method to hide loading indicator
     * */
    private void hideLoadingIndicator() {
        rotateLoadingIndicator.stop();
        rotateLoadingIndicator.setVisibility(View.GONE);
    }

    /*
     * Helper method to setup the recyclerView
     * */
    private void initReyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsArticleAdapter(newsArticles, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            newsRecyclerView.setLayoutManager(layoutManager);
            newsRecyclerView.setAdapter(newsAdapter);
        } else {
            newsAdapter.notifyDataSetChanged();
        }

    }

}