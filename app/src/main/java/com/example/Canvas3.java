package com.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/17.
 */

public class Canvas3 extends View {
    // 1.创建Picture
    private Picture mPicture = new Picture();

    public Canvas3(Context context) {
        this(context, null);
    }

    // 3.在使用前调用(我在构造函数中调用了)
    public Canvas3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        recording();//调用录制
    }

    // 2.录制内容方法
//    private void recording() {
//        // 开始录制 (接收返回值Canvas)
//        Canvas canvas = mPicture.beginRecording(500, 500);
//        //创建一个画笔
//        Paint paint = new Paint();
//        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.FILL);
//
//        //在Canvas中具体操作
//        //位移
//        canvas.translate(250, 250);
//        //绘制一个圆
//        canvas.drawCircle(0, 0, 100, paint);
//        mPicture.endRecording();
//        // 将Picture中的内容绘制在Canvas上
//        //mPicture.draw(canvas);
//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 开始录制 (接收返回值Canvas)
        Canvas canvas1 = mPicture.beginRecording(500, 500);
        //创建一个画笔
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        //在Canvas中具体操作
        //位移
        canvas.translate(250, 250);
        //绘制一个圆
        canvas.drawCircle(0, 0, 100, paint);
        mPicture.endRecording();
        // 将Picture中的内容绘制在Canvas上
        //mPicture.draw(canvas);
//        canvas.drawPicture(mPicture,new RectF(0,0,mPicture.getWidth(),200));
    }
}
