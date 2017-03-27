package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mimi.povely.*;

/**
 * Created by JSY on 2017-03-25.
 */

public class NewMember extends AppCompatActivity {

    Button new_member;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_member);
        getWindow().setWindowAnimations(0);

        new_member = (Button)findViewById(R.id.new_member_button);

        new_member.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), com.example.mimi.povely.MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
