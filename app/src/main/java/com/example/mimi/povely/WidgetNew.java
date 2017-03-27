package com.example.mimi.povely;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ae878 on 2017-03-27.
 */

public class WidgetNew extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_new);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

    }
}
