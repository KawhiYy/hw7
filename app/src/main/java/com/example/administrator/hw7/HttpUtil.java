package com.example.administrator.hw7;

import android.os.HandlerThread;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {
    public static void sendHttpRequest(final String address,
                                       okhttp3.Callback callback) {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                url("https://www.apiopen.top/weatherApi?city=" + address)
                .build();
        client.newCall(request).enqueue(callback);


        Log.d("hhhhhh", "run: 123");

    }
}

