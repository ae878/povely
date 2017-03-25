package com.example.jsy.povely;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import com.example.mimi.povely.R;

public class MainActivity extends AppCompatActivity {
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


