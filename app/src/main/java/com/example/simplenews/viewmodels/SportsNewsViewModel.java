package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.NewsResponse;
import com.example.simplenews.repositories.NewsRepository;

import timber.log.Timber;

public class SportsNewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> sportsNewsResponse;
    private NewsRepository newsRepository;

    //    When a viewmodel object is created fetch the data needed for the fragment
    public void initSportsNewsViewModel() {
//        If the mutable live data is already up to date don't make another request
        if (sportsNewsResponse != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        sportsNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.SPORTS_CATEGORY_QUERY_PARAM);

    }

    public void refreshTopHeadlines() {
        this.sportsNewsResponse = null;
        sportsNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.SPORTS_CATEGORY_QUERY_PARAM);
        Timber.d("Refresh top headlines was called successfully");

    }

    //    Getter method for mutable live data so observers can observe it's changes
    public MutableLiveData<NewsResponse> getSportsNewsResponse() {
        return sportsNewsResponse;
    }
}