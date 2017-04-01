package com.example.mimi.povely;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Koo on 2017-04-02.
 */

public class PhotoListAdapter extends BaseAdapter {
    private Context context = null;
    private ArrayList<Photo_item> gData = new ArrayList<Photo_item>();
    public PhotoListAdapter(Context context) { this.context = context; }
    public void addItem(Photo_item item) { gData.add(item); }

    public int getCount() { return gData.size(); }

    public Photo_item getItem(int pos) { return gData.get(pos); }

    public long getItemId(int pos) { return pos;}

    public View getView(int pos, View ConvertView, ViewGroup parent) {
        Photo_ListView lv;
        if(ConvertView == null) {
            lv = new Photo_ListView(context, gData.get(pos));
        } else {
            lv = (Photo_ListView)ConvertView;
        }
        lv.setIcon(gData.get(pos).getIcon());
        return lv;
    }

}
