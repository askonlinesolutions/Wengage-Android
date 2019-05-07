package com.askonlinesolutions.wengage;

import android.app.Application;

import com.wassa.facelytics.all.FacelyticsUtils;

public class Main extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacelyticsUtils.loadPlugin(this);
    }
}
