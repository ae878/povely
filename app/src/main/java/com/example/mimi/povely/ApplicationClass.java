package com.example.mimi.povely;

import android.app.Application;
import android.content.res.Configuration;

import com.tsengvn.typekit.Typekit;

/**
 * Created by Koo on 2017-03-28.
 * 폰트 관련 클래스이니 건들 필요 없음.
 * 폰트 변경시 createFromAsset()안의 경로만 바꿔주면됨.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/Yoon310.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/Yoon330.ttf"))
                .addItalic(Typekit.createFromAsset(this, "fonts/Yoon320.ttf")); //일기장 제목

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
