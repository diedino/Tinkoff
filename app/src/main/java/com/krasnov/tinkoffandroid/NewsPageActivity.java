package com.krasnov.tinkoffandroid;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.krasnov.tinkoffandroid.models.NewsPage;
import com.krasnov.tinkoffandroid.viewmodels.MainActivityViewModel;
import com.krasnov.tinkoffandroid.viewmodels.NewsPageViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewsPageActivity extends AppCompatActivity {

    private static final String TAG = "NewsPageActivity";
    private TextView mDate_news;
    private TextView mText_news;
    private TextView mContent_news;
    private NewsPageViewModel mNewsPageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        mDate_news = findViewById(R.id.date_news);
        mText_news = findViewById(R.id.text_news);
        mContent_news = findViewById(R.id.content_news);

        mNewsPageViewModel = ViewModelProviders.of(this).get(NewsPageViewModel.class);

        Intent intent = getIntent();
        mNewsPageViewModel.init(intent.getStringExtra("id"));

        mNewsPageViewModel.getNews().observe(this, new Observer<NewsPage>() {
            @Override
            public void onChanged(@Nullable NewsPage newsPage) {
                mDate_news.setText(toDate(newsPage.getTitle().getPublicationDate().getMilliseconds()));
                mText_news.setText(newsPage.getTitle().getText());
                mContent_news.setText(Html.fromHtml(newsPage.getContent()));
            }
        });
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
