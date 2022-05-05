package com.example.class22b_and_316332857_1;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MSPV3.initHelper(this);
    }
}