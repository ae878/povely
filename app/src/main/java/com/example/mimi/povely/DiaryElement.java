package com.example.mimi.povely;

import java.io.Serializable;

/**
 * Created by Koo on 2017-05-29.
 */

public class DiaryElement implements Serializable {
    String title;
    String date;
    String content;

    public DiaryElement(String title, String date, String content) {
        this.title = title;
        this.date = date;
        this.content = content;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() { return content; }
    public String getDate() {
        return date;
    }

}
