package com.example.mimi.povely;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by ae878 on 2017-03-27.
 */

public class WidgetNew extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_new);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

    }
}
