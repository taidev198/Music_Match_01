package com.sunasterisk.musixmatch.application;

import android.app.Application;

public class MusixMatchApplication extends Application {
    private static MusixMatchApplication sApplication;

    public static MusixMatchApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
