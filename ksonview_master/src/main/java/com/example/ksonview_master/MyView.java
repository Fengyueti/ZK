package com.example.ksonview_master;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {
    private Paint mPaint;//画笔
    private int circleX;//圆的x轴起始坐标
    private int circleY;//圆的y轴起始坐标
    private int mRaduis=100;//原半径，px
    /*
     * new 一个控件的调用的
     * */
    public MyView(Context context) {
       this(context,null);
    }
    /*
    * 自定义view中包含自定义属性的，属性是什么
    * */
    public MyView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);

    }
/*
* 自定义view中，包含属性和样式
*
* */
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
/*
* 初始化对象的方法
* */
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);//红色
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setStyle(Paint.Style.FILL);//实心
       // mPaint.setStyle(Paint.Style.STROKE);//空心
       // mPaint.setStrokeWidth(20);
    }
/*
* 测量
* */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
/*
* 在onmeasure之后
* */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
    //绘制

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("重绘："+"==============");
        canvas.drawCircle(circleX,circleY,mRaduis,mPaint);
    }
    /*
    * 触摸事件
    *
    * */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://按下
                circleX = (int) event.getX();//获取距离父控件的x轴坐标
//                circleX = (int) event.getRawX();//获取距离屏幕边缘的x轴坐标
                circleY = (int) event.getY();
                invalidate();//重绘

                break;
            case MotionEvent.ACTION_MOVE://滑动
                circleX = (int) event.getX();
                circleY = (int) event.getY();

                invalidate();

                break;
            case MotionEvent.ACTION_UP://抬起
                break;
        }
        return true;//消费时间
    }
}
