package com.example.mimi.povely;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

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
    private LinearLayout photo_list;

    //사진으로 전송시 되돌려 받을 번호
    static int REQUEST_PICTURE=1;
    //앨범으로 전송시 돌려받을 번호
    static int REQUEST_PHOTO_ALBUM=2;
    //첫번째 이미지 아이콘 샘플 이다.
    static String SAMPLEIMG="ic_launcher.png";

    ListView lv = null;

    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy. MM. dd. HH:mm ", Locale.KOREA);
    String time = sdfNow.format(new Date(System.currentTimeMillis()));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_new);
        getWindow().setWindowAnimations(0); // acitivity 효과 없애기

        diary_title = (EditText)findViewById(R.id.diary_title);
        now_length = (TextView)findViewById(R.id.now_length);
        diary_content = (EditText)findViewById(R.id.diary_content);
        photo_list = (LinearLayout)findViewById(R.id.photo_list);

        findViewById(R.id.go_back_in_diary_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide_keyboard();
                finish();
            }
        });

        date_view = (TextView) findViewById(R.id.make_date);
        date_view.setText(time);

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

        if(getIntent().getExtras() != null) {
            DiaryElement diaryElement = (DiaryElement) getIntent().getExtras().get("diary");
            diary_title.setText(diaryElement.getTitle());
            date_view.setText(diaryElement.getDate());
            diary_content.setText(diaryElement.getContent());
        }
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }

        new AlertDialog.Builder(this)
                .setTitle("업로드할 이미지 선택")
                .setPositiveButton("사진 촬영", cameraListener)
                .setNeutralButton("앨범 선택", albumListener)
                .setNegativeButton("취소", cancelListener)
                .show();
    }
    private void checkPermissions(){

        if (ContextCompat.checkSelfPermission(Diarynew.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(Diarynew.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(Diarynew.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1052);

        }

    }
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case 1052: {
                // If request is cancelled, the result
                // arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED ){

                    // permission was granted.

                } else {
                    // Permission denied - Show a message
                    // to inform the user that this app only works
                    // with these permissions granted
                }
                return;
            }

        }
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
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_PICTURE);
    }
    public void delete_photo(View V) {
        RelativeLayout parent = (RelativeLayout) V.getParent();
        photo_list.removeView(parent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bitmap bm = null;
            if(requestCode==REQUEST_PICTURE){
                bm = (Bitmap) data.getExtras().get("data");
            }
            if(requestCode==REQUEST_PHOTO_ALBUM){
                try {
                    bm = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                }
            }
            LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RelativeLayout Item_Container = (RelativeLayout) inflater.inflate(R.layout.photo_item_in_diary_new, null);
            ImageView Item_img = (ImageView) Item_Container.getChildAt(0);
            Item_img.setImageBitmap(bm);
            photo_list.addView(Item_Container);
        }
    }
    public void hide_keyboard() {
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
    public void complete_btn(View v) throws MalformedURLException, ProtocolException {
        final String title = diary_title.getText().toString();
        final String date = date_view.getText().toString();
        final String content = diary_content.getText().toString();
        int photo_count = photo_list.getChildCount();
        Bitmap[] photos = new Bitmap[photo_count];

        for(int cnt = 0; cnt < photo_count; cnt++) {
            ImageView photo_item = (ImageView) ((RelativeLayout)photo_list.getChildAt(cnt)).getChildAt(0);
            BitmapDrawable d = (BitmapDrawable)(photo_item).getDrawable();
            Bitmap b = d.getBitmap();
            saveBitmaptoJpeg(b, "test0", "" + cnt);
            photos[cnt] = b;
        }

        ////////////////////////////////////////////////////////////////////////////////
        ApiInterface apiService = ApiInterface.retrofit.create(ApiInterface.class);
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("date", date);
        map.put("description", content);

        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        // Get Absolute Path in External Sdcard
        String foler_name = "/"+"test0"+"/";
        String file_name = "0.jpg";

        File imgFile = new File(ex_storage + foler_name + file_name);


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imgFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imgFile.getName(), requestFile);


        Call<JSONObject> call = apiService.getResult(body, map);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                JSONObject RESPONSE = response.body();
                Intent goToList = new Intent(getApplicationContext(), DiaryList.class);
                startActivity(goToList);
                finish();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                System.out.println("RESPONSE ERROR!!!");
                System.out.println(t.toString());
            }
        });


    }
    public interface ApiInterface {
        @Multipart
        @POST("posting")
        Call<JSONObject> getResult(@Part MultipartBody.Part photo,
                                   @PartMap Map<String, String> params);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();

        public final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.217.172:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
    public static void saveBitmaptoJpeg(Bitmap bitmap,String folder, String name){
        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        // Get Absolute Path in External Sdcard
        String foler_name = "/"+folder+"/";
        String file_name = name+".jpg";
        String string_path = ex_storage+foler_name;

        File file_path;
        try{
            file_path = new File(string_path);
            if(!file_path.isDirectory()){
                file_path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(string_path+file_name);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

        }catch(FileNotFoundException exception){
            Log.e("FileNotFoundException", exception.getMessage());
        }catch(IOException exception){
            Log.e("IOException", exception.getMessage());
        }
    }
}
