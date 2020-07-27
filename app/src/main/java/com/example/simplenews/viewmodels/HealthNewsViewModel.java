package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.NewsResponse;
import com.example.simplenews.repositories.NewsRepository;

import timber.log.Timber;

public class HealthNewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> HealthNewsResponse;
    private NewsRepository newsRepository;

    //    When a viewmodel object is created fetch the data needed for the fragment
    public void initHeatlhNewsViewModel() {
//        If the mutable live data is already up to date don't make another request
        if (HealthNewsResponse != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        HealthNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.HEALTH_CATEGORY_QUERY_PARAM);

    }

    public void refreshTopHeadlines() {
        this.HealthNewsResponse = null;
        HealthNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.HEALTH_CATEGORY_QUERY_PARAM);
        Timber.d("Refresh top headlines was called successfully");

    }

    //    Getter method for mutable live data so observers can observe it's changes
    public MutableLiveData<NewsResponse> getHealthNewsResponse() {
        return HealthNewsResponse;
    }
}