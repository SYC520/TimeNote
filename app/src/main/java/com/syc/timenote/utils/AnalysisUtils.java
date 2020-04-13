/*
 *    项目名称:TimeNote
 *    文件名称:AnalysisUtils.java
 *    Date:4/13/20 2:05 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AnalysisUtils {

    /**
    * @Description: 读取登录用户名
    * @Param: [context]
    * @return: java.lang.String
    */
    public static String getLoginUserName(Context context){
        SharedPreferences sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        return  sp.getString("loginUserName","");
    }
    /**
    * @Description: 读取登录状态
    * @Param: [context]
    * @return: boolean
    */
    public static boolean isLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        return sp.getBoolean("isLogin",false);
    }
}
