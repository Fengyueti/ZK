package com.example.lenovo.fengyue0102.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.fengyue0102.R;
import com.example.lenovo.fengyue0102.adapter.MAdapter;
import com.example.lenovo.fengyue0102.api.UserApi;
import com.example.lenovo.fengyue0102.contract.IShowContract;
import com.example.lenovo.fengyue0102.entity.User;
import com.example.lenovo.fengyue0102.presenter.ShowPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements IShowContract.IShowView {
    private ShowPresenter presenter;
    private MAdapter mAdapter;
    private GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
/*
* 操作数据的方法
*
* */
    private void initData() {
        presenter.show(UserApi.USERSHOW,null);
    }
/*
* 操作视图的方法
*
* */
    private void initView() {
        gv =findViewById(R.id.gv);
        presenter = new ShowPresenter(this);
        mAdapter = new MAdapter(this);
    }


    @Override
    public void onSuccess(String result) {
        User user = new Gson().fromJson(result, User.class);
        final List<User.Data.TJ.LL> list = user.getData().getTuijian().getList();
        mAdapter.setList(list);
        gv.setAdapter(mAdapter);
        gv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pid = list.get(position).getPid();
                final Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccessMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
