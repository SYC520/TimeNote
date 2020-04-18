/*
 *    项目名称:TimeNote
 *    文件名称:SettingActivity.java
 *    Date:4/13/20 3:40 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.syc.timenote.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title, back;
    private RelativeLayout change_password, night_mode, app_update, share_app, policy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        back = findViewById(R.id.tv_back);
        title = findViewById(R.id.tv_mian_title);
        title.setText(R.string.setting_title);
        change_password = findViewById(R.id.rl_change_password);
        night_mode = findViewById(R.id.rl_night_mode);
        app_update = findViewById(R.id.rl_update);
        share_app = findViewById(R.id.rl_share);
        policy = findViewById(R.id.rl_policy);
        back.setOnClickListener(this);
        change_password.setOnClickListener(this);
        night_mode.setOnClickListener(this);
        app_update.setOnClickListener(this);
        share_app.setOnClickListener(this);
        policy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_change_password:
                Toast.makeText(this,"点击了修改密码",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_night_mode:
                Toast.makeText(this,"点击了夜间模式",Toast.LENGTH_SHORT).show();
                break;
                case R.id.rl_share:
                Toast.makeText(this,"点击了分享应用",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_update:
                Toast.makeText(this,"点击了更新",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_policy:
                Toast.makeText(this,"点击了隐私协议",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
