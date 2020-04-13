/*
 *    项目名称:TimeNote
 *    文件名称:RegisterActivity.java
 *    Date:4/10/20 5:07 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syc.timenote.R;
import com.syc.timenote.utils.MD5Utils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "md5";
    private TextView mTv_title, mTv_back;
    private EditText mEt_userName, mEt_userPass, mEt_userPass_confirm;
    private Button mBtn_register;
    private String userName, userPass, userPass_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mTv_back = findViewById(R.id.tv_back);
        mTv_title = findViewById(R.id.tv_mian_title);
        mEt_userName = findViewById(R.id.et_register_userName);
        mEt_userPass = findViewById(R.id.et_register_userPass);
        mEt_userPass_confirm = findViewById(R.id.et_register_userPass_confirm);
        mBtn_register = findViewById(R.id.btn_register);
        mTv_back.setOnClickListener(this);
        mTv_title.setText(R.string.register);
        mBtn_register.setOnClickListener(this);
    }

    /**
     * @Description: 获取文本框里的账户信息
     * @Param: []
     * @return: void
     */
    private void getEditString() {
        userName = mEt_userName.getText().toString().trim();
        userPass = mEt_userPass.getText().toString().trim();
        userPass_confirm = mEt_userPass_confirm.getText().toString().trim();
    }

    /**
     * @Description: 判断用户是否存在
     * @Param: [userName]
     * @return: boolean
     */
    private boolean isExistUserName(String userName) {
        boolean has_userName = false;
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        String sp_psw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(sp_psw)) {
            has_userName = true;
        }
        return has_userName;
    }

    /**
     * @Description: 注册
     * @Param: [userName, userPass]
     * @return: void
     */
    private void registerUser(String userName, String userPass) {
        String userPass_MD5 = MD5Utils.md5(userPass);
        Log.d(TAG, "registerUser: " + userPass_MD5);
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName, userPass_MD5);         //以用户名为key,密码为value保存
        editor.apply();
    }

    /**
     * @Description: 点击事件逻辑
     * @Param: [v]
     * @return: void
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.btn_register:
                getEditString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userPass)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userPass_confirm)) {
                    Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (!userPass.equals(userPass_confirm)) {
                    Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                } else if (isExistUserName(userName)) {
                    Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "注册成功" + userName + userPass, Toast.LENGTH_SHORT).show();
                    registerUser(userName, userPass);
                    Intent intent = new Intent();
                    intent.putExtra("userName", userName);
                    setResult(RESULT_OK, intent);
                    this.finish();
                }
        }
    }
}
