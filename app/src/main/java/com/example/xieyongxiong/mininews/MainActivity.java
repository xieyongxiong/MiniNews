package com.example.xieyongxiong.mininews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.xieyongxiong.mininews.adapter.recy_adapter;
import com.example.xieyongxiong.mininews.http.HttpMethod;
import com.example.xieyongxiong.mininews.info.title;
import com.example.xieyongxiong.mininews.subscribers.TitleListener;
import com.example.xieyongxiong.mininews.subscribers.TitleSubscriber;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private recy_adapter adapter;
    private TitleListener titleListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);


        titleListener = new TitleListener() {
            @Override
            public void onNext(title t) {
                adapter = new recy_adapter(getBaseContext(),t.getResult());
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClickListener(new recy_adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getBaseContext(),NewsActivity.class);
                        intent.putExtra("content",adapter.getData(position));
                        startActivity(intent);
                    }
                });
            }
        };

        loadTitle();
    }

    public void loadTitle(){
        HttpMethod.getInstance().getTitle(new TitleSubscriber(titleListener,getBaseContext()));
    }



}
