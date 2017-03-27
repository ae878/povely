package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by JSY on 2017-03-25.
 */

public class NewMember extends BaseActivity {

    Button new_member;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_member);

        new_member = (Button)findViewById(R.id.new_member_button);

        new_member.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), com.example.mimi.povely.MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
