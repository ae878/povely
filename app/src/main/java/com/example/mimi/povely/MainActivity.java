package com.example.mimi.povely;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기


    }

    public void onClickCoverImageSetting(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
        intent.setType("image/*"); //이미지만 보이게
        //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }

    public void onClickHome(View v){


    }

    public void onClickDiary(View v){
        Intent intent = new Intent(getApplicationContext(), DiaryList.class);
        startActivity(intent);

    }


    public void onClickGallery(View v){
      // Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
     //  startActivity(intent);

    }

    public void onClickCommunity(View v){
         Intent intent = new Intent(getApplicationContext(), Community.class);
          startActivity(intent);

    }

    public void onClickSetting(View v){
       // Intent intent = new Intent(MainActivity.this, MainActivity.class);
       // startActivity(intent);
       // overridePendingTransition(0, 0);

    }


    //이미지 선택작업을 후의 결과 처리
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                //이미지가 한계이상(?) 크면 불러 오지 못하므로 사이즈를 줄여 준다.
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.cover); //커버 사진 추가하기 위함
                relativeLayout.setBackground(new BitmapDrawable(scaled));
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}
