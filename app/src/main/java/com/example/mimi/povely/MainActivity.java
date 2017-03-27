package com.example.mimi.povely;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    ListView listView = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

        final ListViewAdapter adapter = new ListViewAdapter();

        listView = (ListView) findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        adapter.addItem("코코 생일 ","D-25");
        adapter.addItem("예방 접종 ","D-25");
        adapter.addItem("태어난지 ","+1225");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // 아이템 클릭시 ( 아직 구현 안함)
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position) ;


                // TODO : use item data.
            }
        }) ;

    }

    public void onClickWidgetPlus(View v){
        Intent intent = new Intent(getApplicationContext(), WidgetNew.class);
        startActivity(intent);

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

    public void onClickCoverImageSetting(View v){

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
        intent.setType("image/*"); //이미지만 보이게
        //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
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
                ImageView imageView = (ImageView) findViewById(R.id.cover); //커버 사진 추가하기 위함
                imageView.setBackground(new BitmapDrawable(scaled));
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}
