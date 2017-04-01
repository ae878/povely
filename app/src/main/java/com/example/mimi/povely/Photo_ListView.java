package com.example.mimi.povely;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Koo on 2017-04-01.
 */

public class Photo_ListView extends RelativeLayout {
    private ImageView icon;

    public Photo_ListView(Context context, Photo_item photo) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.diary_list_item, this, true);

        icon = (ImageView)findViewById(R.id.photo_item);
    }
    public void setIcon(Bitmap bm) {
        this.icon.setBackground(new ShapeDrawable(new OvalShape()));
        this.icon.setClipToOutline(true);
        this.icon.setImageBitmap(bm);
    }
}
