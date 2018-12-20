package com.example.administrator.hw7;

import android.support.v7.widget.RecyclerView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<String> cn;
    private List<Integer> cp;
    private int position;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView)itemView.findViewById(R.id.tx1);
            textView2 = (TextView)itemView.findViewById(R.id.tx2);
            imageView = (ImageView)itemView.findViewById(R.id.iv);
        }
    }
    public CityAdapter(List<String> cn,List<Integer> cp){
        this.cn = cn;
        this.cp = cp;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_item,viewGroup,false);
        position = i;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtil.sendHttpRequest(cn.get(position), new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                // String responseData = response.body().string();

                                if (!response.isSuccessful()) {
                                    onFailure(call, new IOException("Unexpected Code: " + response));
                                } else {
                                    String responseData = response.body().string();
                                    WeatherActivity.parseJSONWithJSONObject(responseData);

                                }
                            }
                        });
                        WeatherActivity.ActionStart(viewGroup.getContext());


                    }
                }).start();
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder viewHolder, int i) {
        viewHolder.textView1.setText(cn.get(i));
        viewHolder.textView2.setText("想查看"+cn.get(i)+"的天气，click here");
        viewHolder.imageView.setImageResource(cp.get(i));
    }


    @Override
    public int getItemCount() {
        return cn.size();
    }


}