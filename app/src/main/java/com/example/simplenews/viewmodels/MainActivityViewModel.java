package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.NewsResponse;
import com.example.simplenews.repositories.NewsRepository;

import timber.log.Timber;

public class MainActivityViewModel extends ViewModel {


    private MutableLiveData<NewsResponse> newsResponseMutableLiveData;
    private NewsRepository newsRepository;

    //    When a viewmodel object is created fetch the data needed for the activitiy
    public void initNewsViewModel() {
//        If the mutable live data is already up to date don't make another request
        if (newsResponseMutableLiveData != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        newsResponseMutableLiveData = newsRepository.getTopHeadlines();

    }

    public void refreshTopHeadlines() {
        this.newsResponseMutableLiveData = null;
        newsResponseMutableLiveData = newsRepository.getTopHeadlines();
        Timber.d("Refresh top headlines was called successfully");

    }

    //    Getter method for mutable live data so observers can observe it's changes
    public MutableLiveData<NewsResponse> getNewsResponseMutableLiveData() {
        return newsResponseMutableLiveData;
    }

}
