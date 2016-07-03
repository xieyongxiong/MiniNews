package com.example.xieyongxiong.mininews.subscribers;

import android.content.Context;
import android.util.Log;

import com.example.xieyongxiong.mininews.info.title;

import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by xieyongxiong on 16-7-3.
 */
public class TitleSubscriber extends Subscriber<Response<title>>{

    private TitleListener titleListener;

    private Context context;

    public TitleSubscriber(TitleListener titleListener,Context context){
        this.titleListener = titleListener;
        this.context = context;
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Response<title> titleResponse) {

        title t= titleResponse.body();
        titleListener.onNext(t);
    }
}
