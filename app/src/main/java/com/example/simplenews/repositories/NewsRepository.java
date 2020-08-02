package com.example.simplenews.repositories;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsIntent.Builder;
import androidx.lifecycle.MutableLiveData;

import com.example.simplenews.R;
import com.example.simplenews.models.Article;
import com.example.simplenews.models.NewsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewsRepository {
    private static NewsRepository newsRepository;
    private NewsAPI newsAPI;
    public static final String SCIENCE_CATEGORY_QUERY_PARAM = "science";
    public static final String BUSINESS_CATEGORY_QUERY_PARAM = "business";
    public static final String HEALTH_CATEGORY_QUERY_PARAM = "health";
    public static final String SPORTS_CATEGORY_QUERY_PARAM = "sports";
    public static final String TECHNOLOGY_CATEGORY_QUERY_PARAM = "technology";
    public static final String ENTERTAINMENT_CATEGORY_QUERY_PARAM = "entertainment";
    public static final String GENERAL_CATEGORY_QUERY_PARAM = "general";


    private List<Article> freshArticles;


    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    /*
     * Private constructor because nobody should be creating this object direcly
     * */
    private NewsRepository() {
        newsAPI = RetrofitClient.getRetrofitInstance().create(NewsAPI.class);
    }

    /*
     * Get top headlines method returns general news headlines from all categories
     * */
    public MutableLiveData<NewsResponse> getTopHeadlines() {
        MutableLiveData<NewsResponse> topHeadlines = new MutableLiveData<>();
        newsAPI.getTopicSpecificHeadline(GENERAL_CATEGORY_QUERY_PARAM).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    topHeadlines.setValue(response.body());
                    Timber.d("Network call was succesful here is the response code " + response.code());
                } else {
                    Timber.d("Network call was unsuccesful " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Timber.d("Network call completely failed lol");
                topHeadlines.setValue(null);
            }
        });
        return topHeadlines;

    }

    public MutableLiveData<NewsResponse> getTopicSpecificHeadlines(String topic) {
        MutableLiveData<NewsResponse> topicSpecificHeadlines = new MutableLiveData<>();
        newsAPI.getTopicSpecificHeadline(topic).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    topicSpecificHeadlines.setValue(response.body());
                    Timber.d("Network call was succesful here is the response code " + response.code());
                } else {
                    Timber.d("Network call was unsuccesful " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Timber.d("Network call completely failed lol");
                topicSpecificHeadlines.setValue(null);
            }
        });
        return topicSpecificHeadlines;
    }

    /*
     * Method for opening customChromeTab
     * @param: articles String url
     * */
    public static void openCustomChromeTab(Context context, String url, int color) {
        Builder tabBuilder = new Builder();
        tabBuilder.setToolbarColor(color);
        tabBuilder.addDefaultShareMenuItem();
        CustomTabsIntent customTabsIntent = tabBuilder.build();
        customTabsIntent.launchUrl(context, Uri.parse(url));
    }

}
