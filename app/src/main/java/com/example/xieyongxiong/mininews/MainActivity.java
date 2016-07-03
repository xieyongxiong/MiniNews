package com.example.xieyongxiong.mininews;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.xieyongxiong.mininews.adapter.recy_adapter;
import com.example.xieyongxiong.mininews.info.title;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private recy_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);

        loadData();
    }
    public void loadData(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/")
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewService service = retrofit.create(NewService.class);

        service.getTitleInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<title>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("thg","onError");
                    }

                    @Override
                    public void onNext(Response<title> titleResponse) {

                        Log.e("thg","onNext");
                        title t = titleResponse.body();
                        adapter = new recy_adapter(getApplicationContext(),t.getResult());
                        recyclerView.setAdapter(adapter);
                    }
                });







//        Call<title> call = service.getTitleInfo();
//        call.enqueue(new Callback<title>() {
//            @Override
//            public void onResponse(Call<title> call, Response<title> response) {
//                title t = response.body();
//                adapter = new recy_adapter(getApplicationContext(),t.getResult());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        recyclerView.setAdapter(adapter);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<title> call, Throwable t) {
//
//            }
//        });
    }
}
