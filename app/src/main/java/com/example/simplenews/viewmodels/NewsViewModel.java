package com.example.simplenews.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simplenews.models.Example;
import com.example.simplenews.repositories.NewsRepository;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<Example> mutableLiveData;
    private NewsRepository newsRepository;

    public void initNewsViewModel() {
        if(mutableLiveData !=  null) {
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getTopHeadlines();

    }

    public MutableLiveData<Example> getNewsRepository() {
        return mutableLiveData;
    }
}
