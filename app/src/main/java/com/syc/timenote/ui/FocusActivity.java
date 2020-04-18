/*
 *    项目名称:TimeNote
 *    文件名称:FocusActivity.java
 *    Date:4/17/20 4:24 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syc.timenote.R;

import cn.iwgang.countdownview.CountdownView;

public class FocusActivity extends AppCompatActivity {
    private static final String TAG = "FocusActivity";
    private CircleRelativeLayout crlayout;
    private CountdownView mCountdownView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        initView();

        mCountdownView.start(5000);
        for (int time = 0; time < 1000; time++) {
            mCountdownView.updateShow(time);
        }
        mCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                Log.d(TAG, "onEnd: ");
            }
        });
    }

    private void initView() {
        crlayout = findViewById(R.id.crlayout);
        crlayout.setColor(getResources().getColor(R.color.colorTransparent));
        crlayout.setAlhpa(160);
        mCountdownView = findViewById(R.id.countdown);
    }

}
