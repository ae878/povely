package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
    }
    public void onClickNewMember(View v){
        Intent intent = new Intent(getApplicationContext(),NewMember.class);
        startActivity(intent);
    }
    public void onClickLogin(View v){
        Intent intent = new Intent(getApplicationContext(), com.example.mimi.povely.MainActivity.class);
        startActivity(intent);
    }
}


