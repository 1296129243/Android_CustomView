package com.example.leafloading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.R;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/22.
 */

public class LeafLoadingActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final int REFRESH_PROGRESS = 0x10;
    private LeafLoadingView mLeafLoadingView;
    private SeekBar mAmpireSeekBar;
    private SeekBar mDistanceSeekBar;
    private TextView mMplitudeText;
    private TextView mDisparityText;
    private View mFanView;
    private Button mClearButton;
    private int mProgress = 0;

    private TextView mProgressText;
    private View mAddProgress;
    private SeekBar mFloatTimeSeekBar;

    private SeekBar mRotateTimeSeekBar;
    private TextView mFloatTimeText;
    private TextView mRotateTimeText;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(800));
                        mLeafLoadingView.setProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaf_loading_layout);
        initViews();
    }

    private void initViews() {
        mFanView = findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = AnimationUtils.initRotateAnimation(false, 1500, true, Animation.INFINITE);
        mFanView.startAnimation(rotateAnimation);
        mClearButton = findViewById(R.id.clear_progress);
        mClearButton.setOnClickListener(this);

        mLeafLoadingView = findViewById(R.id.leaf_loading);
        mMplitudeText = findViewById(R.id.text_ampair);
        mMplitudeText.setText(getString(R.string.current_mplitude, String.valueOf(mLeafLoadingView.getMiddleAmplitude())));

        mDisparityText = findViewById(R.id.text_disparity);
        mDisparityText.setText(getString(R.string.current_Disparity,
                String.valueOf(mLeafLoadingView.getMplitudeDisparity())));

        mAmpireSeekBar = findViewById(R.id.seekBar_ampair);
        mAmpireSeekBar.setOnSeekBarChangeListener(this);
        mAmpireSeekBar.setProgress(mLeafLoadingView.getMiddleAmplitude());
        mAmpireSeekBar.setMax(50);

        mDistanceSeekBar = findViewById(R.id.seekBar_distance);
        mDistanceSeekBar.setOnSeekBarChangeListener(this);
        mDistanceSeekBar.setProgress(mLeafLoadingView.getMplitudeDisparity());
        mDistanceSeekBar.setMax(20);

        mAddProgress = findViewById(R.id.add_progress);
        mAddProgress.setOnClickListener(this);
        mProgressText = findViewById(R.id.text_progress);

        mFloatTimeText = findViewById(R.id.text_float_time);
        mFloatTimeSeekBar = findViewById(R.id.seekBar_float_time);
        mFloatTimeSeekBar.setOnSeekBarChangeListener(this);
        mFloatTimeSeekBar.setMax(5000);
        mFloatTimeSeekBar.setProgress((int) mLeafLoadingView.getLeafFloatTime());
        mFloatTimeText.setText(getResources().getString(R.string.current_float_time,
                String.valueOf(mLeafLoadingView.getLeafFloatTime())));

        mRotateTimeText = findViewById(R.id.text_rotate_time);
        mRotateTimeSeekBar = findViewById(R.id.seekBar_rotate_time);
        mRotateTimeSeekBar.setOnSeekBarChangeListener(this);
        mRotateTimeSeekBar.setMax(5000);
        mRotateTimeSeekBar.setProgress((int) mLeafLoadingView.getLeafRotateTime());
        mRotateTimeText.setText(getResources().getString(R.string.current_float_time,
                String.valueOf(mLeafLoadingView.getLeafRotateTime())));
    }

    @Override
    public void onClick(View view) {
        if (view == mClearButton) {
            mLeafLoadingView.setProgress(0);
            mHandler.removeCallbacksAndMessages(null);
            mProgress = 0;
        } else if (view == mAddProgress) {
            mProgress++;
            mLeafLoadingView.setProgress(mProgress);
            mProgressText.setText(String.valueOf(mProgress));
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mAmpireSeekBar) {
            mLeafLoadingView.setMiddleAmplitude(progress);
            mMplitudeText.setText(getString(R.string.current_mplitude,
                    String.valueOf(progress)));
        } else if (seekBar == mDistanceSeekBar) {
            mLeafLoadingView.setMplitudeDisparity(progress);
            mDisparityText.setText(getString(R.string.current_Disparity,
                    String.valueOf(progress)));
        } else if (seekBar == mFloatTimeSeekBar) {
            mLeafLoadingView.setLeafFloatTime(progress);
            mFloatTimeText.setText(getResources().getString(R.string.current_float_time,
                    String.valueOf(progress)));
        } else if (seekBar == mRotateTimeSeekBar) {
            mLeafLoadingView.setLeafRotateTime(progress);
            mRotateTimeText.setText(getResources().getString(R.string.current_rotate_time,
                    String.valueOf(progress)));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
