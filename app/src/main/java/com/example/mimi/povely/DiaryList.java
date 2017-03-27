package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Koo on 2017-03-13.
 * 일기 눌렀을때, 맨 처음 나오게 되는 작성한 일기들 리스트.
 */

public class DiaryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);

        getWindow().setWindowAnimations(0);// activity 전환 효과 없애기

        ImageButton to_diary_new = (ImageButton)findViewById(R.id.to_diary_new);


        to_diary_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Diarynew.class);
                startActivity(intent);
            }
        });
    }
    public void go_home(View view) {
        finish();
    }
    public void go_community(View view) {
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);
        finish();
    } /*
    public void go_settings(View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
        finish();
    }
    public void go_gallery(View view) {
        Intent intent = new Intent(getApplicationContext(), Gallery.class);
        startActivity(intent);
        finish();
    } */
}
