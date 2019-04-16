package com.bishe.qiuzhi.app;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getApp() {
        if (context != null) {
            return context;
        } else {
            throw new NullPointerException();
        }
    }
}
