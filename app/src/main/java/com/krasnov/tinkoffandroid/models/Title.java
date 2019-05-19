package com.krasnov.tinkoffandroid.models;

import com.google.gson.annotations.SerializedName;

public class Title {

    @SerializedName("text")
    private String text;
    @SerializedName("publicationDate")
    private PublicationDate publicationDate;

    public Title(String text, PublicationDate publicationDate) {
        this.text = text;
        this.publicationDate = publicationDate;
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
