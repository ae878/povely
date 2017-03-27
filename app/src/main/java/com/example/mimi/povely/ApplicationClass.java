package com.example.mimi.povely;

import android.app.Application;
import android.content.res.Configuration;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Koo on 2017-03-28.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/Yoon310.ttf"));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
