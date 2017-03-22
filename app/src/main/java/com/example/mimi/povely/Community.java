package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ae878 on 2017-03-22.
 */

public class Community extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

    }


    public void onClickHome(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    public void onClickDiary(View v) {
        Intent intent = new Intent(getApplicationContext(), DiaryList.class);
        startActivity(intent);

    }


    public void onClickGallery(View v) {
        // Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
        //  startActivity(intent);

    }

    public void onClickCommunity(View v) {
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);

    }

    public void onClickSetting(View v) {
        // Intent intent = new Intent(MainActivity.this, MainActivity.class);
        // startActivity(intent);
        // overridePendingTransition(0, 0);

    }
}
