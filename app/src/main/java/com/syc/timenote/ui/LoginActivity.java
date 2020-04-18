/*
 *    项目名称:TimeNote
 *    文件名称:LoginActivity.java
 *    Date:4/5/20 6:32 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.syc.timenote.R;
import com.syc.timenote.utils.MD5Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "LoginActivity";
    private String userName, userPass;
    private TextView mTvRegister;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView back, title;
    private TextView recover_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        // 初始化控件
        back = findViewById(R.id.tv_back);
        title = findViewById(R.id.tv_mian_title);
        title.setText(R.string.login);
        mBtnLogin = findViewById(R.id.btn_login);
        mTvRegister = findViewById(R.id.login_register);
        mEtUsername = findViewById(R.id.et_login_userName);
        mEtPassword = findViewById(R.id.et_login_password);
        recover_password = findViewById(R.id.login_recover_password);
        // 设置点击事件监听器
        back.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
    }

    /**
     * @Description: 通过账户名获取密码, 没有账户时返回""
     * @Param: [userName]
     * @return: java.lang.String
     */
    private String getUserPass(String userName) {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sp.getString(userName, "");
    }

    /**
     * @Description: 保存登录状态和登录账户名
     * @Param: [status, userName]
     * @return: void
     */
    private void saveLoginStatus(boolean status, String userName) {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
//        存入登录时的账户名
        editor.putBoolean("isLogin", status);
        editor.putString("loginUserName", userName);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
//            从注册界面传过来的用户名
            String userName = data.getStringExtra("userName");
            if (!TextUtils.isEmpty(userName)) {
                mEtUsername.setText(userName);
//                设置光标位置
                mEtUsername.setSelection(userName.length());
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            // 跳转到注册界面
            case R.id.login_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.btn_login:
                String et_userName = mEtUsername.getText().toString().trim();
                String et_userPass = mEtPassword.getText().toString().trim();
                String et_userPass_md5 = MD5Utils.md5(et_userPass);
                userPass = getUserPass(et_userName);
                if (TextUtils.isEmpty(et_userName))
                    Toast.makeText(this, "请输入账户", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(et_userPass))
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(userPass))
                    Toast.makeText(this, "用户" + et_userName + "不存在,请注册...", Toast.LENGTH_SHORT).show();
                else if (!TextUtils.isEmpty(userPass) && !et_userPass_md5.equals(userPass))
                    Toast.makeText(this, "密码输入错误", Toast.LENGTH_SHORT).show();
                else if (!TextUtils.isEmpty(userPass) && et_userPass_md5.equals(userPass)) {
                    userName = et_userName;
                    Toast.makeText(this, "登录成功...", Toast.LENGTH_SHORT).show();
//                    保存登录状态和用户名
                    saveLoginStatus(true, userName);
//                    把登录成功的状态传到主活动
                    Intent delivery_status = new Intent();
                    delivery_status.putExtra("isLogin", true);
                    setResult(RESULT_OK, delivery_status);
                    this.finish();
                }
                break;

        }
    }
}