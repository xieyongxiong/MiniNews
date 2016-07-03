package com.example.xieyongxiong.mininews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xieyongxiong.mininews.R;

import java.util.List;

/**
 * Created by xieyongxiong on 16-7-2.
 */
public class recy_adapter extends RecyclerView.Adapter<recy_adapter.myViewHolder> {

    private List<String> data;
    private LayoutInflater inflater;

    public recy_adapter(Context context , List<String> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        for(String s:data){
            Log.e("thg s",s);
        }
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item,parent,false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public myViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
