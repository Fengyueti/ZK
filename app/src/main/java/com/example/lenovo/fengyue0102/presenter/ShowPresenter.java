package com.example.lenovo.fengyue0102.presenter;

import android.os.Handler;

import com.example.lenovo.fengyue0102.contract.IShowContract;
import com.example.lenovo.fengyue0102.entity.User;
import com.example.lenovo.fengyue0102.model.ShowModel;
import com.example.lenovo.fengyue0102.net.RequestCallback;

import java.util.HashMap;

public class ShowPresenter extends IShowContract.IShowPresenter {
    Handler handler=new Handler();
    private ShowModel showModel;
    private IShowContract.IShowView iShowView;

    public ShowPresenter(IShowContract.IShowView iShowView) {
        this.showModel = new ShowModel();
        this.iShowView = iShowView;
    }

    @Override
    public void show(String api,HashMap<String, String> params) {
        if(showModel!=null){
            showModel.show(api,params, new RequestCallback() {
                @Override
                public void failure(final String msg) {
                    if(iShowView!=null){
                        iShowView.onFailure(msg);
                    }
                }

                @Override
                public void success(String result) {
                    if(iShowView!=null){
                        iShowView.onSuccess(result);
                    }
                }


                @Override
                public void successmsg(final String msg) {
                    if(iShowView!=null){
                        iShowView.onSuccessMsg(msg);
                    }
                }
            });
        }
    }

    public void destroy(){
        if(iShowView!=null){
            iShowView=null;
        }
    }
}
