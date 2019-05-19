package com.krasnov.tinkoffandroid.network;

import com.krasnov.tinkoffandroid.models.ItemAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/v1/news")
    Call<ItemAPI> getAllItems();
}
