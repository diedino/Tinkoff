package com.krasnov.tinkoffandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemAPI {

    @SerializedName("payload")
    private List<News> payload;

    public List<News> getPayload() {
        return payload;
    }

    public void setPayload(List<News> payload) {
        this.payload = payload;
    }
}
