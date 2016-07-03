package com.example.xieyongxiong.mininews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xieyongxiong.mininews.R;

import java.util.List;

/**
 * Created by xieyongxiong on 16-7-2.
 */
public class recy_adapter extends RecyclerView.Adapter<recy_adapter.myViewHolder> {

    private List<String> data;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public recy_adapter(Context context , List<String> data) {
        this.data = data;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item,parent,false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {

        holder.textView.setText(data.get(position));

        if(onItemClickListener != null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.textView,pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        LinearLayout linearLayout;
        public myViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.title);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.layout);
        }
    }

    public String getData(int position){
        return data.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
