package com.example.administrator.hw7;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class
MainActivity extends AppCompatActivity {
    private List<String> CityN = new ArrayList<>();
    private List<Integer> CityP = new ArrayList<>();
    private String CityName[] = {"北京","上海","重庆","成都","深圳","南京",
            "广州","天津","石家庄","青岛","厦门"};
    private  int CityPicture[] = {R.drawable.xxmm,R.drawable.xxmm,
            R.drawable.xxmm,R.drawable.xxmm,R.drawable.xxmm,
            R.drawable.xxmm,R.drawable.xxmm,R.drawable.xxmm,
            R.drawable.xxmm,R.drawable.xxmm,R.drawable.xxmm,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.INTERNET

                },
                1000);
        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        initData(CityName,CityPicture);
        CityAdapter ca = new CityAdapter(CityN,CityP);
        recyclerView.setAdapter(ca);

    }
    public void initData(String []a,int []b){
        for(int i =0;i<11;i++){
            CityN.add(a[i]);
            CityP.add(b[i]);

        }

    }
}
