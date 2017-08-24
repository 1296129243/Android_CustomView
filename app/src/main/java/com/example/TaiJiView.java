package com.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/15.
 */

public class TaiJiView extends View {
    private Paint whitePaint;//白色画笔
    private Paint blackPaint;//黑色画笔

    public TaiJiView(Context context) {
      this(context,null);
    }

    public TaiJiView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    //在构造函数中初始化画笔
    public TaiJiView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();//初始化画笔
    }

    private void initPaints() {
        whitePaint = new Paint();
        //设置画笔锯齿和颜色
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);

        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);

    }
    //在onDraw中使用Canvas绘制内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1.确定画布大小
        int width = canvas.getWidth();//画布的宽度
        int height = canvas.getHeight();//画布的高度
        //将Canvas坐标原点移动到画布中心
        canvas.translate(width/2,height/2);// 移动坐标原点到画布中心

        canvas.drawColor(Color.GRAY);//绘制背景色
        //在实际绘制之前调用旋转函数
        canvas.rotate(degress);//旋转画布

        //绘制两个半圆
        int radius = Math.min(width, height) / 2 - 100;//太极半径
        RectF rect = new RectF(-radius,-radius,radius,radius);//绘制区域
        canvas.drawArc(rect,90,180,true,blackPaint);//绘制黑色半圆
        canvas.drawArc(rect,-90,180,true,whitePaint);//绘制白色半圆

        //绘制2个小圆
        int smallRadius = radius / 2;//小圆半径为大圆的一半
        /**
         * 参数1,2是确定小圆中心的位置，画一个黑色的和白色的园，
         * 黑色圆的一半覆盖在白色大半圆，同理。
         */
        canvas.drawCircle(0,-smallRadius,smallRadius,blackPaint);
        canvas.drawCircle(0,smallRadius,smallRadius,whitePaint);

        //绘制鱼眼（两个更小的圆）
        canvas.drawCircle(0,-smallRadius,smallRadius/4,whitePaint);
        canvas.drawCircle(0,smallRadius,smallRadius/4,blackPaint);

    }
    //设置旋转角度变量，并提供外部访问接口
    private float degress = 0;//旋转角度
    public void setRotate(float degress){
        this.degress = degress;
        invalidate();//重绘界面
    }
}
