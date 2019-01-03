package com.example.lenovo.fengyue0102.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.lenovo.fengyue0102.api.UserApi;
import com.example.lenovo.fengyue0102.contract.IShowContract;
import com.example.lenovo.fengyue0102.entity.User;
import com.example.lenovo.fengyue0102.net.OkhttpCallback;
import com.example.lenovo.fengyue0102.net.OkhttpUtils;
import com.example.lenovo.fengyue0102.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.OkHttpClient;

public class ShowModel implements IShowContract.IShowModel {
    Handler handler=new Handler();
    @Override
    public void show(String api,HashMap<String, String> params, final RequestCallback requestCallback) {
                        OkhttpUtils.getmInstance().doPost(api, params, new OkhttpCallback() {
                            @Override
                            public void onFailure(String msg) {
                                if(requestCallback!=null){
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            requestCallback.failure("网络不稳定，请稍后再试");
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onSuccess(final String result) {
                                if(!TextUtils.isEmpty(result)){
                                    if(requestCallback!=null){
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                requestCallback.success(result);
                                            }
                                        });
                                    }
                }
            }
        });
    }
}
