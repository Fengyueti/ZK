package com.example.rk7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.xlistview.src.me.maxwin.view.XListView;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private MAdapter mAdapter;
    private XListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title =findViewById(R.id.title);
        lv =findViewById(R.id.lv);
        mAdapter = new MAdapter(this);
        lv.setAdapter(mAdapter);

    }
}
