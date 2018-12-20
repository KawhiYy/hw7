package com.example.administrator.hw7;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

public class WeatherActivity extends AppCompatActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    String code;
    String msg;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        tv1 = (TextView)findViewById(R.id.tv11);
        tv2 = (TextView)findViewById(R.id.tv12);
        tv3 = (TextView)findViewById(R.id.tv13);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET}, 1000);


    }
    public static void ActionStart(Context context){
        Intent intent = new Intent(context,WeatherActivity.class);
        context.startActivity(intent);

    }

    public static void parseJSONWithJSONObject(String jsondata){
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String code = jsonObject.getString("code");
                String msg = jsonObject.getString("msg");
                String data = jsonObject.getString("data");
                WeatherActivity wa = new WeatherActivity();
                wa.ShowResponse(code,msg,data);
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ShowResponse(final String s1, final String s2, final String s3) {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tv1.setText(s1);
                tv2.setText(s2);
                tv3.setText(s3);

            }
        });
    }


}

