package com.krasnov.tinkoffandroid.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.krasnov.tinkoffandroid.models.ItemAPI;
import com.krasnov.tinkoffandroid.models.ItemPageAPI;
import com.krasnov.tinkoffandroid.models.News;
import com.krasnov.tinkoffandroid.models.NewsPage;
import com.krasnov.tinkoffandroid.network.GetDataService;
import com.krasnov.tinkoffandroid.network.GetDataServicePage;
import com.krasnov.tinkoffandroid.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsPageRepository {

    public interface GetDataCallback {
        void onGetData(NewsPage newsData);
        void onError();
    }

    private static final String TAG = "NewsPageRepository";
    private static NewsPageRepository instance;
    private NewsPage newsPage;
    private ItemPageAPI itemPageAPI;
    private String id;

    public static NewsPageRepository getInstance() {
        instance = new NewsPageRepository();
        return instance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MutableLiveData<NewsPage> getNews(){
        final MutableLiveData<NewsPage> data = new MutableLiveData<>();
        setNews(new NewsPageRepository.GetDataCallback() {
            @Override
            public void onGetData(NewsPage newsData) {
                newsPage = newsData;
                //dataSet = newsData;
                data.setValue(newsData);
                Log.d(TAG, ""+newsData.getTitle().getText());
            }

            @Override
            public void onError() {

            }
        });
        return data;
    }

    private void setNews(final GetDataCallback getDataCallback){
        GetDataServicePage service = RetrofitClientInstance.getRetrofitInstance().create(GetDataServicePage.class);
        Call<ItemPageAPI> call = service.getAllItems(id);
        Log.d(TAG, ""+id);
        Log.d(TAG, "URL: "+call.request().url());
        call.enqueue(new Callback<ItemPageAPI>() {
            @Override
            public void onResponse(Call<ItemPageAPI> call, Response<ItemPageAPI> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response is successful");
                    itemPageAPI = response.body();
                    getDataCallback.onGetData(itemPageAPI.getPayload());
                }
                else {
                    getDataCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<ItemPageAPI> call, Throwable t) {
                getDataCallback.onError();
                Log.d(TAG, "onFailure "+ t.getMessage());
            }
        });
    }
}
