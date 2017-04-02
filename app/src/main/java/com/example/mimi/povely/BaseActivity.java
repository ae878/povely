package com.example.mimi.povely;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Koo on 2017-03-28.
 * 앞으로 만드는 모든 액티비티는 이 BaseActivity를 상속 받을 것!!!! ( 폰트 적용을 위함!!! )
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

}
