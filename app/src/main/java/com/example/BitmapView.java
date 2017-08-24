package com.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/18.
 */

public class BitmapView extends View {
    private final Context context;

    public BitmapView(Context context) {
        this(context,null);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 通过BitmapFactory从不同位置获取Bitmap:
         * 资源文件(drawable/mipmap/raw):
         * @param canvas
         */
        Bitmap bitmap = null;
        try {
            InputStream is = context.getAssets().open("image1.jpg");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // canvas.drawBitmap(bitmap,new Matrix(),new Paint());
        canvas.drawBitmap(bitmap,5,5,new Paint());

    }
}
