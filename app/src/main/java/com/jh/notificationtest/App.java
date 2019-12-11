package com.jh.notificationtest;

import android.app.Application;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    public static App getApp() {
        return instance;
    }


}
