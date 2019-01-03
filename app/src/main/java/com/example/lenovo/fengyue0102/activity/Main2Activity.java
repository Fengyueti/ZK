package com.example.lenovo.fengyue0102.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.fengyue0102.R;
import com.example.lenovo.fengyue0102.adapter.MAdapter;
import com.example.lenovo.fengyue0102.api.UserApi;
import com.example.lenovo.fengyue0102.contract.IShowContract;
import com.example.lenovo.fengyue0102.entity.ProductBean;
import com.example.lenovo.fengyue0102.net.OkhttpUtils;
import com.example.lenovo.fengyue0102.presenter.ShowPresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class Main2Activity extends AppCompatActivity implements IShowContract.IShowView {

    private ShowPresenter presenter;
    private TextView title;
    private TextView price;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title =findViewById(R.id.title);
        price =findViewById(R.id.price);
        img =findViewById(R.id.img);
        final Intent intent = getIntent();
        final String pid = intent.getStringExtra("pid");
        presenter = new ShowPresenter(this);
        HashMap<String,String> params=new HashMap<>();
        params.put("pid",pid+"");
        presenter.show(UserApi.PRODUCT_SHOW,params);
    }

    @Override
    public void onSuccess(String result) {
         ProductBean productBean = new Gson().fromJson(result, ProductBean.class);
         ProductBean.Data data = productBean.getData();
         title.setText(data.getTitle());
         String[] split = data.getImages().split("!");
         Glide.with(this).load(split[0]).into(img);
         price.setText(data.getPrice());
    }

    @Override
    public void onSuccessMsg(String msg) {

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        OkhttpUtils.getmInstance().cancelAllTas();
    }
}
