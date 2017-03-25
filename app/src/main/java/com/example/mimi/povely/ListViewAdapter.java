package com.example.mimi.povely;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mimi on 2017-02-25.
 */

public class ListViewAdapter extends BaseAdapter {


    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>(); // 실제 보여줄 리스트

    public ListViewAdapter(){

    }
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.homelistview_item, parent, false);
        }

        TextView dayName = (TextView) convertView.findViewById(R.id.dayname) ;
        TextView dDay = (TextView) convertView.findViewById(R.id.dday) ;


        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        dayName.setText(listViewItem.getDayMame());
        dDay.setText(listViewItem.getDDay());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(String dayName, String dDay) {
        ListViewItem item = new ListViewItem();

        item.setDayMame(dayName);
        item.setDDay(dDay);

        listViewItemList.add(item);
    }
}
