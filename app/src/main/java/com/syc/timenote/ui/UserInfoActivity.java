/*
 *    项目名称:TimeNote
 *    文件名称:UserInfoActivity.java
 *    Date:4/14/20 3:22 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.syc.timenote.R;
import com.syc.timenote.bean.User;
import com.syc.timenote.model.SQLiteHelperImpl;
import com.syc.timenote.utils.AnalysisUtils;

import org.w3c.dom.Text;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "username";
    private String userName;
    private TextView mTv_title, back, mTv_userName, mTv_nickname, mTv_sex, mTv_signature;
    private RelativeLayout mRl_userName, mRl_nickName, mRl_sex, mRl_signature;
    private AlertDialog.Builder builder;
    private int choice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userName = AnalysisUtils.getLoginUserName(this);
        initView();

    }

    private void initView() {
        User user = SQLiteHelperImpl.getInstance(this).getUserInfo(userName);
        back = findViewById(R.id.tv_back);
        mTv_title = findViewById(R.id.tv_mian_title);
        mTv_title.setText(R.string.userInfo);
        mTv_userName = findViewById(R.id.tv_userName);
        mTv_userName.setText(user.getUserName());
        mTv_nickname = findViewById(R.id.tv_nickname);
        mTv_nickname.setText(user.getNickName());
        mTv_sex = findViewById(R.id.tv_sex);
        mTv_sex.setText(user.getSex());
        mTv_signature = findViewById(R.id.tv_signature);
        mTv_signature.setText(user.getSignature());
        mRl_nickName = findViewById(R.id.rl_nickname);
        mRl_sex = findViewById(R.id.rl_sex);
        mRl_signature = findViewById(R.id.rl_signature);
        back.setOnClickListener(this);
        mRl_nickName.setOnClickListener(this);
        mRl_sex.setOnClickListener(this);
        mRl_signature.setOnClickListener(this);
    }

    /**
     * @Description: 资料修改会话框
     * @Param: [dailogtitle, key]
     * @return: void
     */
    private void showDialogInput(int dailogtitle, final String key) {
        final EditText editText = new EditText(this);
        User user = SQLiteHelperImpl.getInstance(this).getUserInfo(userName);
        builder = new AlertDialog.Builder(this).setTitle(dailogtitle).setIcon(R.drawable.nav_head);
        if (key.equals("sex")) {
            String male = getString(R.string.male);
            String female = getString(R.string.female);
            if (user.getSex().equals("")) {
                choice = -1;
            } else if (user.getSex().equals(male)) {
                choice = 0;
            } else if (user.getSex().equals(female)) {
                choice = 1;
            }
            final String[] items = {male, female};
            builder.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    choice = which;
                }
            }).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (choice != -1) {
                        SQLiteHelperImpl.getInstance(getApplicationContext()).updateUserInfo(userName, key, items[choice]);
                    }
                }
            });
        } else {
            if (key.equals("nickName")) {
                editText.setText(user.getNickName());
            }
            else if (key.equals("signature"))
                editText.setText(user.getSignature());
            builder.setView(editText).setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelperImpl.getInstance(getApplicationContext()).updateUserInfo(userName, key, editText.getText().toString().trim());
                }
            });
        }
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                initView();
            }
        });
        builder.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_nickname:
                showDialogInput(R.string.update_nickName, "nickName");
                break;
            case R.id.rl_signature:
                showDialogInput(R.string.update_signature, "signature");
                break;
            case R.id.rl_sex:
                showDialogInput(R.string.update_sex, "sex");
                break;
        }
    }
}
