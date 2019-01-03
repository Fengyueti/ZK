package com.example.ksonview_master;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


@SuppressLint("AppCompatCustomView")
public class CircleRoundImageView extends ImageView {
    public CircleRoundImageView(Context context) {
        super(context);
    }

    public CircleRoundImageView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleRoundImageView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BitmapDrawable bitmapDrawable= (BitmapDrawable) getDrawable();
        final Bitmap bitmap = bitmapDrawable.getBitmap();
        if(bitmap!=null){
            //对bitmap对象进行圆角转换
            final Bitmap nb = getRoundBitmap(bitmap);
            canvas.drawBitmap(nb,0,0,null);//绘制新的bitmap
        }else{
            super.onDraw(canvas);
        }
    }
    /*
    * 获取圆角bitmap,xfermode,进阶里面一个交集
    *
    * */
    private Bitmap getRoundBitmap(Bitmap bitmap){
        //宽高缩放比
        float widthScale = getMeasuredWidth() / bitmap.getWidth();
        float heightScale= getMeasuredHeight()/bitmap.getHeight();
        //矩阵变换类
        final Matrix matrix = new Matrix();
        matrix.setScale(widthScale,heightScale);
        //bitmap进行变换
        final Bitmap nbit = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        //最终输出对象
        final Bitmap target = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);//创建一个色彩是8888对象
        //创建画布
        final Canvas canvas = new Canvas(target);
        //创建画笔
        final Paint paint = new Paint();
        //创建一个圆角的图形
        final RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());//矩形区域
        canvas.drawRoundRect(rectF,30,30,paint);
        //设置画笔的xfermode模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//设置模式
        //画原始bitmap
        canvas.drawBitmap(nbit,0,0,paint);
        return target;
    }
}
