package com.krasnov.tinkoffandroid.models;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("publicationDate")
    private PublicationDate publicationDate;

    public News(String id, String text, PublicationDate publicationDate) {
        this.id = id;
        this.text = text;
        this.publicationDate = publicationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PublicationDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(PublicationDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
