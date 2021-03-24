package com.wgt.admoblib;

import android.app.Application;

import com.flurry.android.FlurryAgent;

public class App extends Application {

    private static final String FLURRY_KEY = "abc";

    private static App app;

    public static App get() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;


        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this,FLURRY_KEY);

    }

    public void sendTracker(String name) {
        FlurryAgent.logEvent(name);
    }
}
