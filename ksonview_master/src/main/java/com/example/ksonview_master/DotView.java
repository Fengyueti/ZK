package com.example.ksonview_master;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DotView extends View {

    private Paint paint;
    private Paint rectPaint;
    private Paint checkPaint;
    List<DotBean> list=new ArrayList<>();
    private int left,right,top,bottom;
    private Rect rect;

    public DotView(Context context) {
        this(context,null);
    }

    public DotView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        rectPaint =new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);//空心
        rectPaint.setStrokeWidth(10);
        rectPaint.setColor(Color.BLUE);
        checkPaint =new Paint();
        checkPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(list!=null){
            for (DotBean dotBean : list) {
                if(!dotBean.isChecked()){
                    canvas.drawCircle(dotBean.getX(),dotBean.getY(),20,paint);
                }else{
                    canvas.drawCircle(dotBean.getX(),dotBean.getY(),20,checkPaint);
                }
            }
        }
        //100*100正方形
        rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect,rectPaint);
    }
    /*添加点对象
    *
    * */
    public void add(DotBean dotBean){
        if(dotBean!=null){
            list.add(dotBean);
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
               left = (int) event.getX();
               top = (int) event.getY();
               break;
               case MotionEvent.ACTION_MOVE:
                   right = (int) event.getX();
                   bottom = (int) event.getY();
                   for (DotBean dotBean : list) {
                       //矩形区域
                       if(dotBean.getX()>left&&dotBean.getX()<right&&dotBean.getY()>top&&dotBean.getY()<bottom){
                           dotBean.setChecked(true);
                       }else{
                           dotBean.setChecked(false);
                       }
                   }
                   invalidate();
                   break;
                   case MotionEvent.ACTION_UP:
                       break;
        }
        return true;
    }
}
