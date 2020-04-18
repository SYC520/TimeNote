/*
 *    项目名称:TimeNote
 *    文件名称:SQLiteHelperImpl.java
 *    Date:4/14/20 5:58 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.syc.timenote.bean.User;

public class SQLiteHelperImpl implements IDBHelper {
    private static SQLiteHelperImpl instance = null;
    private static SQLiteDatabase db;

    private SQLiteHelperImpl(Context context) {
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public static SQLiteHelperImpl getInstance(Context context) {
        if (instance == null)
            instance = new SQLiteHelperImpl(context);
        return instance;
    }

    /**
     * @Description: 保存个人资料
     * @Param: [user]
     * @return: void
     */
    @Override
    public void saveUserInfo(User user) {
        ContentValues cv = new ContentValues();
        cv.put("userName", user.getUserName());
        cv.put("nickName", user.getNickName());
        cv.put("sex", user.getSex());
        cv.put("signature", user.getSignature());
        db.insert(SQLiteOpenHelper.USER_INFO, null, cv);
    }

    /**
     * @Description: 获取个人资料
     * @Param: [userName]
     * @return: com.syc.timenote.bean.User
     */
    @Override
    public User getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteOpenHelper.USER_INFO +
                " WHERE userName = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        User user = new User();
        while (cursor.moveToNext()){
            user.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            user.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            user.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            user.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
        }
        cursor.close();
        return user;
    }

    /**
     * @Description: 更新个人资料
     * @Param: [userName, key, value]
     * @return: void
     */
    @Override
    public void updateUserInfo(String userName, String key, String value) {
        ContentValues cv = new ContentValues();
        cv.put(key,value);
        db.update(SQLiteOpenHelper.USER_INFO,cv,"userName = ?",new String[]{userName});
    }
}
