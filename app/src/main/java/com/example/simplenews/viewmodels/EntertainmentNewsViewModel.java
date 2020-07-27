package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.NewsResponse;
import com.example.simplenews.repositories.NewsRepository;

import timber.log.Timber;

public class EntertainmentNewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> EntertainmentNewsResponse;
    private NewsRepository newsRepository;

    //    When a viewmodel object is created fetch the data needed for the fragment
    public void initEntertainmentNewsViewModel() {
//        If the mutable live data is already up to date don't make another request
        if (EntertainmentNewsResponse != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        EntertainmentNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.ENTERTAINMENT_CATEGORY_QUERY_PARAM);

    }

    public void refreshTopHeadlines() {
        this.EntertainmentNewsResponse = null;
        EntertainmentNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.ENTERTAINMENT_CATEGORY_QUERY_PARAM);
        Timber.d("Refresh top headlines was called successfully");

    }

    //    Getter method for mutable live data so observers can observe it's changes
    public MutableLiveData<NewsResponse> getEntertainmentNewsResponse() {
        return EntertainmentNewsResponse;
    }

}