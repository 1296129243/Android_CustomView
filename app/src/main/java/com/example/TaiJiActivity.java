package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/8/15.
 */

public class TaiJiActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TaiJiView taiJiView = new TaiJiView(this);
        setContentView(taiJiView);
        Handler handler = new Handler() {
            private float degress = 0;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                taiJiView.setRotate(degress += 5);
                this.sendEmptyMessageDelayed(0, 80);
            }
        };
        handler.sendEmptyMessageDelayed(0, 20);
    }
}
