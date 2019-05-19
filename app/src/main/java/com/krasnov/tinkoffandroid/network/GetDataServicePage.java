package com.krasnov.tinkoffandroid.network;

import com.krasnov.tinkoffandroid.models.ItemPageAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataServicePage {

    @GET("/v1/news_content")
    Call<ItemPageAPI> getAllItems(@Query("id") String id);
}
