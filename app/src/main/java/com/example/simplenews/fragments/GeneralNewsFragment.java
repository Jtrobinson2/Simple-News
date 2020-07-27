package com.example.simplenews.fragments;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.simplenews.R;
import com.example.simplenews.adapters.NewsArticleAdapter;
import com.example.simplenews.adapters.RecyclerItemClickListener;
import com.example.simplenews.models.Article;
import com.example.simplenews.viewmodels.EntertainmentNewsViewModel;
import com.example.simplenews.viewmodels.GeneralNewsViewModel;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class GeneralNewsFragment extends Fragment {

    private GeneralNewsViewModel generalNewsViewModel;
    private RecyclerView newsRecyclerView;
    private NewsArticleAdapter newsAdapter;
    private ArrayList<Article> newsArticles = new ArrayList<>();
    private RotateLoading rotateLoadingIndicator;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static GeneralNewsFragment newInstance() {
        return new GeneralNewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.general_news_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //        Planting timber debug tree here because this joint refuses to work when planted in the application class
        Timber.plant(new Timber.DebugTree());


        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        newsRecyclerView = view.findViewById(R.id.newsRecyclerView);
        rotateLoadingIndicator = view.findViewById(R.id.rotate_loading_indicator);

//        Getting and setting up the viewmodel
        generalNewsViewModel = new ViewModelProvider(this).get(GeneralNewsViewModel.class);
        generalNewsViewModel.initGeneralNewsViewModel();

//        Setting up the observer for the top headlines live data
        generalNewsViewModel.getGeneralNewsResponse().observe(getViewLifecycleOwner(), newsResponse -> {
            ArrayList<Article> freshNewsArticles = (ArrayList<Article>) newsResponse.getArticles();
            Timber.d("General Mutable Live data changed was observed here it is: " + newsResponse.getArticles().toString());
            refreshNewsRecyclerView(freshNewsArticles);
        });

        initReyclerView();


//        This is not the way to do recyclerview click listeners but this will suffice for now
        newsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), newsRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
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
        swipeRefreshLayout.setOnRefreshListener(() -> {
            generalNewsViewModel.refreshTopHeadlines();
            newsRecyclerView.setVisibility(View.INVISIBLE);
            showLoadingIndicator();
            swipeRefreshLayout.setRefreshing(false);
            hideLoadingIndicator();
            newsRecyclerView.setVisibility(View.VISIBLE);
            /*
             * This toast is here because my news api plan won't show realtime news, it only
             * shows new news headlines every hour. The Majority of the use cases won't have
             * users waiting in the app for an hour for new news headlines
             * therefore this toast is acceptable because 99% of the time when they pull to refresh
             * the news will be the same news.
             * */
            Toast.makeText(getContext(), "News already up-to-date", Toast.LENGTH_SHORT).show();

        });
    }

    /*
     * Helper method that refreshes topHeadlinesRecyclerView with new articles
     * @param: list of new article objects from a network request
     * */
    private void refreshNewsRecyclerView(List<Article> freshArticles) {
        Timber.d("refreshRecyclerView was called");
        newsRecyclerView.setVisibility(View.INVISIBLE);
        showLoadingIndicator();
        newsAdapter.clearNewsArticles();
        newsAdapter.addAll(freshArticles);
        hideLoadingIndicator();
        newsRecyclerView.setVisibility(View.VISIBLE);
        newsAdapter.notifyDataSetChanged();
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
            showLoadingIndicator();
            newsAdapter = new NewsArticleAdapter(newsArticles, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            newsRecyclerView.setLayoutManager(layoutManager);
            newsRecyclerView.setAdapter(newsAdapter);
            hideLoadingIndicator();
        } else {
            newsAdapter.notifyDataSetChanged();
        }

    }
}
