package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Koo on 2017-05-31.
 */

public class DiaryShow extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_show);
        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

        DiaryElement diaryElement = (DiaryElement) getIntent().getExtras().get("diary");

        TextView title = (TextView) findViewById(R.id.diary_title);
        TextView date = (TextView) findViewById(R.id.make_date);
        TextView content = (TextView) findViewById(R.id.diary_content);

        title.setText(diaryElement.getTitle());
        date.setText(diaryElement.getDate());
        content.setText(diaryElement.getContent());
    }
    public void goBack(View v) {
        Intent intent = new Intent(getApplicationContext(), DiaryList.class);
        startActivity(intent);
        finish();
    }
    public void goEdit(View v) {
        Intent intent = new Intent(getApplicationContext(), Diarynew.class);
        intent.putExtra("diary", (DiaryElement) getIntent().getExtras().get("diary"));
        startActivity(intent);
        //DiaryDB dd = new DiaryDB(getApplicationContext(), "DiaryBook.db", null, 1);
        //dd.delete((DiaryElement) getIntent().getExtras().get("diary"));
        finish();
    }
}
