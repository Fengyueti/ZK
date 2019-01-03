package com.example.lenovo.fengyue0102.contract;

import com.example.lenovo.fengyue0102.entity.User;
import com.example.lenovo.fengyue0102.net.RequestCallback;

import java.util.HashMap;

public interface IShowContract {
    public abstract class IShowPresenter{
        public abstract void show(String api,HashMap<String,String> params);
    }
    interface IShowModel{
        void show(String api,HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface IShowView{
        void onSuccess(String result);
        void onSuccessMsg(String msg);
        void onFailure(String msg);
    }
}
