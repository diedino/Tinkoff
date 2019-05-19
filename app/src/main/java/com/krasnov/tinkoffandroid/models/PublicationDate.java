package com.krasnov.tinkoffandroid.models;

import com.google.gson.annotations.SerializedName;

public class PublicationDate {

    @SerializedName("milliseconds")
    private String milliseconds;

    public PublicationDate(String milliseconds) {
        this.milliseconds = milliseconds;
    }

    public String getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = milliseconds;
    }
}
