/*
 *    项目名称:TimeNote
 *    文件名称:IDBHelper.java
 *    Date:4/14/20 5:52 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.model;

import com.syc.timenote.bean.User;

public interface IDBHelper {
    void saveUserInfo(User user);

    User getUserInfo(String userName);

    void updateUserInfo(String userName, String key, String value);
}
