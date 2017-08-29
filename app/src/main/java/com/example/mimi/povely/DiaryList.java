package com.example.mimi.povely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Koo on 2017-03-13.
 * 일기 눌렀을때, 맨 처음 나오게 되는 작성한 일기들 리스트.
 */

public class DiaryList extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);

        getWindow().setWindowAnimations(0);// activity 전환 효과 없애기

        ImageButton to_diary_new = (ImageButton) findViewById(R.id.to_diary_new);
        to_diary_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Diarynew.class);
                startActivity(intent);
            }
        });

        final DiaryListAdapter diaryListAdapter = new DiaryListAdapter(getApplicationContext());

        ListView lv = (ListView) findViewById(R.id.diary_list);
        lv.setAdapter(diaryListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DiaryShow.class);
                intent.putExtra("diary", diaryListAdapter.getItem(position));
                startActivity(intent);
                finish();
            }
        });
        ListInterface listInterface = ListInterface.retrofit.create(ListInterface.class);
        Call<List<ListItem>> call = listInterface.getList();
        call.enqueue(new Callback<List<ListItem>>() {
            @Override
            public void onResponse(Call<List<ListItem>> call, Response<List<ListItem>> response) {
                List<ListItem> result = response.body();
                for(int cnt = 0; cnt < result.size(); cnt++) {
                    diaryListAdapter.addItem(new DiaryElement(result.get(cnt).getTitle(), result.get(cnt).getDate(), result.get(cnt).getDescription()));
                    diaryListAdapter.notifyDataSetChanged();
                }
                System.out.println(result.size() + "!!!!!!!!!!");
            }
            @Override
            public void onFailure(Call<List<ListItem>> call, Throwable t) {
                System.out.println(t.toString() + "!!!!!!!!!!");
            }
        });
    }
    public class ListItem {
        String title;
        String description;
        String date;
        String photo;

        String getTitle() {
            return title;
        }
        String getDescription() {
            return description;
        }
        String getDate() {
            return date;
        }
        String getPhoto() {
            return photo;
        }
        void setTitle(String title) {
            this.title = title;
        }
        void setDescription(String description) {
            this.description = description;
        }
        void setDate(String date) {
            this.date = date;
        }
        void setPhoto(String photo) {
            this.photo = photo;
        }

    }
    public interface ListInterface {

        @GET("list")
        Call<List<ListItem>> getList();

        public final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.217.172:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public void go_home(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void go_community(View view) {
        Intent intent = new Intent(getApplicationContext(), Community.class);
        startActivity(intent);
        finish();
    }
    public void go_gallery(View view) {
        Intent intent = new Intent(getApplicationContext(), Gallery.class);
        startActivity(intent);
        finish();
    }/*
    public void go_settings(View view) {
        Intent intent = new Intent(getApplicationContext(), Settings.class);
        startActivity(intent);
        finish();
    }*/
    public void slide_menu(View view) {
        finish();
    }
}
