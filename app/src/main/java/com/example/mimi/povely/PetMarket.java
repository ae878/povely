package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by JSY on 2017-04-03.
 */

public class PetMarket extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_market);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

    }

    public void onClickGoBack(View v){
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);
        finish();
    }
}
