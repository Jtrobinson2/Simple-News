package com.example.simplenews.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.simplenews.R;

import timber.log.Timber;

public class ArticleWebViewActivity extends AppCompatActivity {
    private WebView articleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web_view);

        String articleUrl = getIntent().getExtras().getString("URL");
        if (!articleUrl.isEmpty()) {
            articleWebView = findViewById(R.id.article_web_view);

            articleWebView.getSettings().setLoadsImagesAutomatically(true);
            articleWebView.getSettings().setJavaScriptEnabled(true);
            // Enable responsive layout
            articleWebView.getSettings().setUseWideViewPort(true);
// Zoom out if the content width is greater than the width of the viewport
            articleWebView.getSettings().setLoadWithOverviewMode(true);
            articleWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            articleWebView.setWebViewClient(new CustomWebViewClient());
            articleWebView.loadUrl(articleUrl);
        } else {
            Toast.makeText(this, "Error loading page, press back to return", Toast.LENGTH_LONG).show();
            Timber.d("Intent that launched this activity had an empty url");
        }

    }

    private class CustomWebViewClient extends WebViewClient {
        /*
         * Overriding both url loading method because one is deprecated
         * in API 24 (the one below) but needed to enable this feature on older devices
         * The other method is the new one but doesn't work on older devices
         * */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        /*
         * The default behavior when a user clicks on a link inside the webpage is to open the systems default browser app. This can break the user experience of the app users.
         * To keep page navigation within the WebView and hence within the app, we need to create a subclass of WebViewClient,
         * and override its shouldOverrideUrlLoading(WebView webView, String url) method.
         * */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(String.valueOf(request.getUrl()));
            return true;
        }
    }


}

