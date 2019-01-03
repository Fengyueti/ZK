package com.example.ksonview_master;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class dotActivity extends Activity {

    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot);
        dotView = findViewById(R.id.dotview);
    }
/*
* 随即添加点
* */
    public void add(View view) {
        final DotBean dotBean = new DotBean();
        dotBean.setX(new Random().nextInt(600));
        dotBean.setY(new Random().nextInt(1000));
        dotBean.setChecked(false);
        dotView.add(dotBean);
    }
/*
*清除
 */
    public void clear(View view) {

    }
}
