package com.example.xieyongxiong.mininews.subscribers;

import com.example.xieyongxiong.mininews.info.News;

/**
 * Created by xieyongxiong on 16-7-3.
 */
public interface NewsListener {
    void onNext(News n);
}
