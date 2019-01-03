package com.example.lenovo.fengyue0102.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    Handler handler=new Handler();
    private static OkhttpUtils mInstance;
    private OkHttpClient okHttpClient;

    public OkhttpUtils() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OkhttpUtils getmInstance() {
        if(mInstance==null){
            synchronized (OkhttpUtils.class){
                if(mInstance==null){
                    mInstance=new OkhttpUtils();
                }
            }
        }
        return mInstance;
    }
    public void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        final FormBody.Builder formbody =new FormBody.Builder();
        if(params!=null){
            for (Map.Entry<String, String> map : params.entrySet()) {
                formbody.add(map.getKey(),map.getValue());
            }
        }
        final Request request = new Request.Builder().url(url).post(formbody.build()).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(okhttpCallback!=null){

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(okhttpCallback!=null){
                                okhttpCallback.onFailure("网络不稳定");
                            }
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                final int code = response.code();
                if(okhttpCallback!=null){
                    if(code==200){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.onSuccess(result);
                            }
                        });
                    }
                }
            }
        });
    }
    public void cancelAllTas(){
        if(okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
