package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /****************************************/
       /* PieView pie = new PieView(this);
//        setContentView(R.layout.custom_activity);
        setContentView(pie);
        String[] names = {"string1","string2","string3","string4","string5"};
        float[] values = {20,30,10,9,21,10};
        ArrayList<PieData> pieDatas = new ArrayList<>();
        for(int i = 0 ; i < names.length ; i ++){
            PieData pieData = new PieData(names[i],values[i]);
            pieDatas.add(pieData);
        }
        pie.setData(pieDatas);//设置数据
        pie.setStartAngle(0);//设置起始角度*/
        /****************************************/
//        Canvas3 canvas3 = new Canvas3(this);
//        setContentView(canvas3);
        /****************************************/
        BitmapView bitmapView = new BitmapView(this);
        setContentView(bitmapView);

    }
}
