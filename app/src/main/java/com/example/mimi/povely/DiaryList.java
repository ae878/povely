package com.example.mimi.povely;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Koo on 2017-03-13.
 * 일기 눌렀을때, 맨 처음 나오게 되는 작성한 일기들 리스트.
 */

public class DiaryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);
    }
}
