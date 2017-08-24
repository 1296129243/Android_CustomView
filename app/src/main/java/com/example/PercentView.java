package com.example;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/8/9.
 */

public class PercentView extends View {
    private static final String TAG = "PercentView";
    private Paint mPaint;
    private RectF rectF;
    private int backgroundColor = Color.GRAY;
    private int gravity = CENTER;
    private float radius;
    private int progerss;
    private int progressColor = Color.BLUE;
    private float centerX = 0;
    private float centerY = 0;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
        initParam(context, attrs);
    }

    public PercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init();
        initParam(context, attrs);
    }

    private void initParam(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        rectF = new RectF();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentView);
        if (typedArray != null) {
            backgroundColor = typedArray.getColor(R.styleable.PercentView_percent_background_color, Color.GRAY);
            progressColor = typedArray.getColor(R.styleable.PercentView_percent_progress_color, Color.BLUE);
            radius = typedArray.getDimension(R.styleable.PercentView_percent_circle_radius, 0);
            progerss = typedArray.getInt(R.styleable.PercentView_percent_circle_progress, 0);
            gravity = typedArray.getInt(R.styleable.PercentView_percent_circle_gravity, CENTER);
            typedArray.recycle();

        }

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        rectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure--widthMode-->" + widthMode);
        Log.e(TAG, "onMeasure--widthSize-->" + widthSize);
        Log.e(TAG, "onMeasure--heightMode-->" + heightMode);
        Log.e(TAG, "onMeasure--heightSize-->" + heightSize);
        /*关于MeasureSpec：

        (1) UPSPECIFIED :父容器对于子容器没有任何限制,子容器想要多大就多大.

        (2) EXACTLY父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.

        (3) AT_MOST子容器可以是声明大小内的任意大小.*/
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        int width = getWidth();
        int height = getHeight();
        Log.e(TAG, "onDraw---->" + width + "*" + height);
        centerX = width / 2;
        centerY = width / 2;
        switch (gravity){
            case LEFT:
                centerX = radius + getPaddingLeft();
                break;
            case TOP:
                centerY = radius + getPaddingTop();
                break;
            case RIGHT:
                centerX = width - radius - getPaddingRight();
                break;
            case BOTTOM:
                centerY = height - radius - getPaddingRight();
                break;
        }
        float left = centerX - radius;
        float top = centerY - radius;
        float right = centerX + radius;
        float bottom = centerX + radius;
        rectF.set(left,top,right,bottom);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GRAY);
        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        int width = getWidth();//获取屏幕的宽度
        int height = getHeight();//获取屏幕的高度
        Log.e(TAG, "onDraw---->" + width + "*" + height);
        float radius = width / 4;//屏幕宽度的1/4为圆的半径
        //第一第二参数表示圆的中心点
        canvas.drawCircle(width / 2, width / 2, radius, mPaint);
        //下面是话圆弧
        mPaint.setColor(Color.BLUE);
        rectF.set(width / 2 - radius, width / 2 - radius, width / 2 + radius, width / 2 + radius);
        canvas.drawArc(rectF, 270, 120, true, mPaint);
    }
}
