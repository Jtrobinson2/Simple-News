package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.NewsResponse;
import com.example.simplenews.repositories.NewsRepository;

import timber.log.Timber;

public class BusinessNewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponse> BusinessNewsResponse;
    private NewsRepository newsRepository;

    //    When a viewmodel object is created fetch the data needed for the fragment
    public void initBusinessNewsViewModel() {
//        If the mutable live data is already up to date don't make another request
        if (BusinessNewsResponse != null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        BusinessNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.BUSINESS_CATEGORY_QUERY_PARAM);

    }

    public void refreshTopHeadlines() {
        this.BusinessNewsResponse = null;
        BusinessNewsResponse = newsRepository.getTopicSpecificHeadlines(NewsRepository.BUSINESS_CATEGORY_QUERY_PARAM);
        Timber.d("Refresh top headlines was called successfully");

    }

    //    Getter method for mutable live data so observers can observe it's changes
    public MutableLiveData<NewsResponse> getBusinessNewsResponse() {
        return BusinessNewsResponse;
    }

}