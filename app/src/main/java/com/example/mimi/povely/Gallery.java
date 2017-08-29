package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Koo on 2017-03-31.
 */

public class Gallery extends BaseActivity {
    private TextView user;
    private TextView title;
    private ImageView img_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        getWindow().setWindowAnimations(0);// activity 전환 효과 없애기

    }

    public void go_home(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void go_diary(View v) {
        Intent intent = new Intent(getApplicationContext(), DiaryList.class);
        startActivity(intent);
        finish();
    }
    public void go_gallery(View v) {
        Intent intent = new Intent(getApplicationContext(), Gallery.class);
        startActivity(intent);
        finish();
    }
    public void go_community(View v) {
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);
        finish();
    }
    /*public void go_settings(View v) {
        finish();
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);
    }*/
}
