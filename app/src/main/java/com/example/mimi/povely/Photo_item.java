package com.example.mimi.povely;

import android.graphics.Bitmap;

/**
 * Created by Koo on 2017-04-01.
 */

public class Photo_item {
    private Bitmap bm;

    public Photo_item(Bitmap bm) {
        this.bm = bm;
    }

    public Bitmap getIcon() { return bm; }
    public String getName() {return "photo"; }
    public String getText() { return "photo"; }
}
