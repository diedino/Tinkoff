package com.krasnov.tinkoffandroid.models;

import com.google.gson.annotations.SerializedName;

public class ItemPageAPI {

    @SerializedName("payload")
    private NewsPage payload;

    public NewsPage getPayload() {
        return payload;
    }

    public void setPayload(NewsPage payload) {
        this.payload = payload;
    }
}
