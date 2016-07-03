package com.example.xieyongxiong.mininews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.xieyongxiong.mininews.http.HttpMethod;
import com.example.xieyongxiong.mininews.info.News;
import com.example.xieyongxiong.mininews.subscribers.NewsListener;
import com.example.xieyongxiong.mininews.subscribers.NewsSubscriber;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends AppCompatActivity {

    @Bind(R.id.title)
    TextView text_title;

    @Bind(R.id.content)
    TextView text_content;

    @Bind(R.id.image)
    ImageView imageView;

    @Bind(R.id.source)
    TextView text_source;

    @Bind(R.id.text_time)
    TextView text_time;

    String content;
    private NewsListener newsListener;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        content = getIntent().getStringExtra("content");

        newsListener = new NewsListener() {
            @Override
            public void onNext(News n) {
                News.ResultBean resultBean = n.getResult().get(0);
                if(!resultBean.getImg().equals("")){
                    Glide.with(getApplicationContext()).load(resultBean.getImg()).into(imageView);
                }else {
                    imageView.setVisibility(View.GONE);
                }
                text_title.setText(resultBean.getFull_title());
                String s = resultBean.getContent().replace("<em>","");
                String content = s.replace("</em>","");
                text_content.setText(content);
                text_time.setText(resultBean.getPdate_src());

                url = resultBean.getUrl();
            }
        };

        loadNews();
    }

    @OnClick(R.id.source)
    public void onClck(){
        Intent intent = new Intent(getApplicationContext(),WebActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    public void loadNews(){
        HttpMethod.getInstance().getNews(new NewsSubscriber(getBaseContext(),newsListener),content);
    }

}
