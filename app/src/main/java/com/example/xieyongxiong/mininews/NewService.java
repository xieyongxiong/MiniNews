package com.example.xieyongxiong.mininews;

import com.example.xieyongxiong.mininews.info.title;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by xieyongxiong on 16-7-2.
 */
public interface NewService  {
    @GET("onebox/news/words?key=471af2544910964e95d364fd3beed54b")
    Observable<Response<title>> getTitleInfo( );

//    Call<title> getTitleInfo();
}
