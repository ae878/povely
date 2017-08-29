package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

/**
 * Created by JSY on 2017-04-03.
 */

public class PetMarket extends BaseActivity{

    RecyclerView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_market);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

        itemList = (RecyclerView)findViewById(R.id.item_list);
        itemList = null;

    }

    public void onClickGoBack(View v){
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);
        finish();
    }

    public void onClickNewSell(View v){
        Intent intent = new Intent(getApplicationContext(), NewSell.class);
        startActivity(intent);
        finish();
    }
}
