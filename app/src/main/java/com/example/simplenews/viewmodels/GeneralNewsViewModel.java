package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.NewsResponse;
import com.example.simplenews.repositories.NewsRepository;

import timber.log.Timber;

public class GeneralNewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> generalNewsResponse;
    private NewsRepository newsRepository;

    //    When a viewmodel object is created fetch the data needed for the fragment
    public void initGeneralNewsViewModel() {
//        If the mutable live data is already up to date don't make another request
        if (generalNewsResponse != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        generalNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.GENERAL_CATEGORY_QUERY_PARAM);

    }

    public void refreshTopHeadlines() {
        this.generalNewsResponse = null;
        generalNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.GENERAL_CATEGORY_QUERY_PARAM);
        Timber.d("Refresh top headlines was called successfully");

    }

    //    Getter method for mutable live data so observers can observe it's changes
    public MutableLiveData<NewsResponse> getGeneralNewsResponse() {
        return generalNewsResponse;
    }
}