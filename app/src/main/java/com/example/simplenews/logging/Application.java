package com.example.simplenews.logging;



import com.example.simplenews.BuildConfig;

import timber.log.Timber;

/*
* This class was only created to plant the Timber debug tree
* */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
