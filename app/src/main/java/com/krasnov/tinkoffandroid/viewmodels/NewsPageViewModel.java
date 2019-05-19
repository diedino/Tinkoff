package com.krasnov.tinkoffandroid.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.krasnov.tinkoffandroid.models.News;
import com.krasnov.tinkoffandroid.models.NewsPage;
import com.krasnov.tinkoffandroid.repositories.NewsPageRepository;

import java.util.List;

public class NewsPageViewModel extends ViewModel {
    private MutableLiveData<NewsPage> mNews;
    private NewsPageRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(String id){
        mRepo = NewsPageRepository.getInstance();
        mRepo.setId(id);
        mNews = mRepo.getNews();
    }

    public LiveData<NewsPage> getNews(){
        return mNews;
    }
    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
