package com.krasnov.tinkoffandroid.models;

import com.google.gson.annotations.SerializedName;

public class NewsPage {

    @SerializedName("content")
    private String content;
    @SerializedName("title")
    private Title title;

    public NewsPage(String content, Title title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
