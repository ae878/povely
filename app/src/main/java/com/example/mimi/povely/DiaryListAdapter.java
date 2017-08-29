package com.example.mimi.povely;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Koo on 2017-05-31.
 */

public class DiaryListAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<DiaryElement> data = new ArrayList<DiaryElement>();
    public DiaryListAdapter(Context context) {
        this.context = context;
    }
    public void addItem(DiaryElement item) {
        data.add(item);
    }
    public void addItems(List<DiaryElement> diaryElements) {
        data.addAll(diaryElements);
    }
    public int getCount() { return data.size(); }
    public DiaryElement getItem(int cnt) {
        return data.get(cnt);
    }
    public long getItemId(int pos) { return pos; }
    public View getView(int pos, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.diary_list_item, parent, false);
        }

        DiaryElement diaryElement = data.get(pos);

        TextView count = (TextView) convertView.findViewById(R.id.diary_count);
        TextView title = (TextView) convertView.findViewById(R.id.diary_title);
        TextView date = (TextView) convertView.findViewById(R.id.diary_date);

        count.setText(String.valueOf(pos + 1));
        title.setText(diaryElement.getTitle());
        date.setText(diaryElement.getDate());

        return convertView;
    }
}
