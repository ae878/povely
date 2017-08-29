package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewSell extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_sell);
        getWindow().setWindowAnimations(0);
    }
    public void OnClickGoBack(View v){
        Intent intent = new Intent(getApplicationContext(), PetMarket.class);
        startActivity(intent);
        finish();
    }
}