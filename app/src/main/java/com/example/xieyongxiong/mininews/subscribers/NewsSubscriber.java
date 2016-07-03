package com.example.xieyongxiong.mininews.subscribers;

import android.content.ContentValues;
import android.content.Context;

import com.example.xieyongxiong.mininews.info.News;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by xieyongxiong on 16-7-3.
 */
public class NewsSubscriber extends Subscriber<Response<News>> {

    private Context context;
    private NewsListener newsListener;

    public NewsSubscriber(Context context, NewsListener newsListener){
        this.context = context;
        this.newsListener = newsListener;
    }
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Response<News> newsResponse) {
        News news = newsResponse.body();
        newsListener.onNext(news);
    }
}
