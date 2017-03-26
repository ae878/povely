package com.example.mimi.povely;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Koo on 2017-03-14.
 */

public class Diarynew extends AppCompatActivity {
    private ImageView iv_UsertPhoto;
    private TextView date_view;
    private Uri mImageCaptureUri;
    private String absolutePath;
    private TextView top_bar_text;
    private EditText diary_title;
    private TextView make_date;
    private EditText diary_content;
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy. MM. dd.   ", Locale.KOREA);
    Date date = new Date();
    String formatDate = sdfNow.format(date);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_new);
        findViewById(R.id.go_back_in_diary_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_UsertPhoto = (ImageView)findViewById(R.id.photo);
        date_view = (TextView)findViewById(R.id.make_date);
        date_view.setText(formatDate);
        top_bar_text = (TextView)findViewById(R.id.top_bar_text);
        top_bar_text.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NanumBarunpenR.ttf"));
        diary_title = (EditText)findViewById(R.id.diary_title);
        diary_title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NanumBarunpenR.ttf"));
        diary_content = (EditText)findViewById(R.id.diary_content);
        diary_content.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NanumBarunpenR.ttf"));
        make_date = (TextView)findViewById(R.id.make_date);
        make_date.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/NanumBarunpenR.ttf"));

        findViewById(R.id.make_new_diary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText)findViewById(R.id.diary_title)).getText().toString();
                String content = ((EditText)findViewById(R.id.diary_content)).getText().toString();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void Camera_Video_Upload(View v) {
        Toast.makeText(this, "카메라/비디오 눌림", Toast.LENGTH_LONG).show();
    }
    public void onClick(View v) {
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
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 2);

    }
    public void doTakePhotoAction() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String Url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), Url));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(resultCode == RESULT_OK) {
                if (requestCode == 1) { //카메라에서 찍기

                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(mImageCaptureUri, "image/*");

                    intent.putExtra("outputX", 200);
                    intent.putExtra("outputy", 200);
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("scale", true);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, 3);

                } else if (requestCode == 2) { //앨범에서 가져오기
                    mImageCaptureUri = data.getData();
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(mImageCaptureUri, "image/*");

                    intent.putExtra("outputX", 200);
                    intent.putExtra("outputy", 200);
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("scale", true);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, 3);
                } else if (requestCode == 3) {
                    final Bundle extras = data.getExtras();
                    Bitmap photo = extras.getParcelable("data");
                    iv_UsertPhoto.setImageBitmap(photo);

                    File f = new File(mImageCaptureUri.getPath());
                    if(f.exists())
                        f.delete();
                }
            }
        }
    }
