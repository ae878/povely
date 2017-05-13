package com.example.mimi.povely;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Koo on 2017-03-14.
 */

public class Diarynew extends BaseActivity {
    private TextView date_view;
    private TextView now_length;
    private Uri mImageCaptureUri;
    private String absolutePath;
    private TextView top_bar_text;
    private EditText diary_title;
    private TextView make_date;
    private EditText diary_content;

    //사진으로 전송시 되돌려 받을 번호
    static int REQUEST_PICTURE=1;
    //앨범으로 전송시 돌려받을 번호
    static int REQUEST_PHOTO_ALBUM=2;
    //첫번째 이미지 아이콘 샘플 이다.
    static String SAMPLEIMG="ic_launcher.png";

    PhotoListAdapter adapter = null;
    HorizontalListView lv = null;

    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy. MM. dd.   ", Locale.KOREA);
    Date date = new Date();
    String formatDate = sdfNow.format(date);

    String year = formatDate.substring(0, 4);
    String month = formatDate.substring(6, 8);
    String day = formatDate.substring(10, 12);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_new);
        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

        diary_title = (EditText)findViewById(R.id.diary_title);
        now_length = (TextView)findViewById(R.id.now_length);
        diary_content = (EditText)findViewById(R.id.diary_content);

        findViewById(R.id.go_back_in_diary_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_keyboard();
                finish();
            }
        });

        date_view = (TextView) findViewById(R.id.make_date);
        date_view.setText(formatDate);

        adapter = new PhotoListAdapter(this);
        lv = (HorizontalListView) findViewById(R.id.photo_list);
        lv.setAdapter(adapter);

        diary_title.addTextChangedListener(new TextWatcher() {
            String strCur;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                strCur = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 20) {
                    diary_title.setText(strCur);
                } else {
                    now_length.setText(String.valueOf(s.length()));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void delete_title(View v) {
        diary_title.setText(null);
    }
    public void input_content(View v) {
        diary_content.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void select_date(View v) {
        hide_keyboard();
        DatePickerDialog dialog = new DatePickerDialog(this, listener, Integer.parseInt(year),
                Integer.parseInt(month) - 1, Integer.parseInt(day));
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String selected_date = year + ". " + (monthOfYear + 1) + ". " + dayOfMonth + ".   ";
            date_view.setText(selected_date);
        }
    };

    public void Camera_Upload(View v) {
        hide_keyboard();
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        new AlertDialog.Builder(this)
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진 촬영", cameraListener)
                .setNeutralButton("앨범 선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }

    public void doTakeAlbumAction() {
        //저장된 사진을 불러오는 함수이다. 즉앨범에있는것인데 인텐트는 ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        //갤러리리의 기본설정 해주도록하자!아래는이미지와그경로를 표준타입으로 설정한다.
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        //사진이 저장된위치(sdcard)에 데이터가 잇다고 지정
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_PHOTO_ALBUM);
    }

    public void doTakePhotoAction() {
        //사진을 찍는 인텐트를 가져온다. 그인텐트는 MediaStore에있는
        //ACTION_IMAGE_CAPTURE를 활용해서 가져온다.
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //그후 파일을 지정해야하는데 File의 앞부분 매개변수에는 파일의 절대경로를 붙여야
        //한다. 그러나 직접 경로를 써넣으면 sdcard접근이 안되므로 ,
        //Environment.getExternalStorageDirectory()로 접근해서 경로를 가져오고 두번째
        //매개 변수에 파일이름을 넣어서 활용 하도록하자!!
        File file=new File(Environment.getExternalStorageDirectory(),SAMPLEIMG);
        //그다음에 사진을 찍을대 그파일을 현재 우리가 갖고있는 SAMPLEIMG로 저장해야
        //한다. 그래서 경로를 putExtra를 이용해서 넣도록 한다. 파일형태로 말이다.
        //그리고 실제로 이파일이 가리키는 경로는 /mnt/sdcard/ic_launcher)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
        //그럼이제사진찍기 인텐트를 불러온다.
        startActivityForResult(intent,REQUEST_PICTURE);
    }
    Bitmap loadPicture(){
        //사진찍은 것을 로드 해오는데 사이즈를 조절하도록하자!!일단 파일을 가져오고
        File file=new File(Environment.getExternalStorageDirectory(),SAMPLEIMG);
        //현재사진찍은 것을 조절하구이해서 조절하는 클래스를 만들자.
        BitmapFactory.Options options=new BitmapFactory.Options();
        //이제 사이즈를 설정한다.
        options.inSampleSize=4;
        //그후에 사진 조정한것을 다시 돌려보낸다.
        return BitmapFactory.decodeFile(file.getAbsolutePath(),options);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==REQUEST_PICTURE){
                //사진을 찍은경우 그사진을 로드해온다.
                adapter.addItem(new Photo_item(loadPicture()));
                adapter.notifyDataSetChanged(); // 어댑터 갱신!!
            }
            if(requestCode==REQUEST_PHOTO_ALBUM){
                try {
                    adapter.addItem(new Photo_item(MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData())));
                    adapter.notifyDataSetChanged(); // 어댑터 갱신!!!
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }
            }
        }
    }
    public void hide_keyboard() {
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}