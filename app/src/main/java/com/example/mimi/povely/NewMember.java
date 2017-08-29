package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JSY on 2017-03-25.
 */

public class NewMember extends BaseActivity {

    Button new_member;
    EditText email;
    EditText password;
    EditText password_check;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_member);
        getWindow().setWindowAnimations(0);

        email = (EditText)findViewById(R.id.new_id);
        password = (EditText)findViewById(R.id.new_password);
        password_check = (EditText)findViewById(R.id.password_check);

        new_member = (Button)findViewById(R.id.new_member_button);

        new_member.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), com.example.mimi.povely.MainActivity.class);
                new Thread() {
                    public void run() {
                        networking();
                    }
                }.start();
                finish();
                startActivity(intent);
            }
        });
    }

    public void networking() {
        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        URL url;
        try {
            url = new URL("http://52.79.217.172/posting");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject request = new JSONObject();
            request.put("user", email.getText().toString());
            request.put("title", password.getText().toString());
            request.put("content", password_check.getText().toString());
            os = conn.getOutputStream();
            os.write(request.toString().getBytes());
            os.flush();

            String response;

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] byteBuffer = new byte[1024];
                byte[] byteData = null;
                int nLength = 0;
                while ((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
                    baos.write(byteBuffer, 0, nLength);
                }
                byteData = baos.toByteArray();

                response = new String(byteData);

                JSONArray responseArray = new JSONArray(response);
                JSONObject responseObject = (JSONObject) responseArray.get(0);
                String user = (String) responseObject.get("user");
                String title = (String) responseObject.get("title");
                String content = (String) responseObject.get("content");
            }


        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (JSONException e) {
        }
    }
}
