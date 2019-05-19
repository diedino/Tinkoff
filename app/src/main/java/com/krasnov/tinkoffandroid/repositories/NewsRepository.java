package com.krasnov.tinkoffandroid.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.krasnov.tinkoffandroid.models.ItemAPI;
import com.krasnov.tinkoffandroid.models.News;
import com.krasnov.tinkoffandroid.network.GetDataService;
import com.krasnov.tinkoffandroid.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    public interface GetDataCallback {
        void onGetData(List<News> newsData);
        void onError();
    }

    private static final String TAG = "NewsRepository";
    private static NewsRepository instance;
    private List<News> dataSet = new ArrayList<>();
    private ItemAPI items;

    //singleton
    public static NewsRepository getInstance() {
        if(instance == null){
            instance = new NewsRepository();
        }
        return instance;
    }

    // getting data
    public MutableLiveData<List<News>> getNews(){
        final MutableLiveData<List<News>> data = new MutableLiveData<>();
        setNews(new GetDataCallback() {
            @Override
            public void onGetData(List<News> newsData) {
                dataSet = milliToDate(newsData);
                //dataSet = newsData;
                data.setValue(dataSet);
            }

            @Override
            public void onError() {

            }
        });
        return data;
    }

    private void setNews(final GetDataCallback getDataCallback){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ItemAPI> call = service.getAllItems();
        call.enqueue(new Callback<ItemAPI>() {
            @Override
            public void onResponse(Call<ItemAPI> call, Response<ItemAPI> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Response is successful");
                    items = response.body();
                    getDataCallback.onGetData(items.getPayload());
                }
                else {
                    getDataCallback.onError();
                }
            }

            @Override
            public void onFailure(Call<ItemAPI> call, Throwable t) {
                getDataCallback.onError();
                Log.d(TAG, "onFailure "+ t.getMessage());
            }
        });
    }

    private List<News> milliToDate(List<News> data) {
        for (int i=0; i<data.size(); i++) {
            data.get(i).getPublicationDate().setMilliseconds(toDate(data.get(i).getPublicationDate().getMilliseconds()));
        }
        return data;
    }

    private String toDate(String milli) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(milli));
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinutes = calendar.get(Calendar.MINUTE);
        return String.format("%02d.%02d.%d %02d:%02d",mDay, mMonth, mYear, mHour, mMinutes);
    }
}
