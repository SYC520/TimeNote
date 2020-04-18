/*
 *    项目名称:TimeNote
 *    文件名称:User.java
 *    Date:4/5/20 6:40 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.bean;

public class User {

    private String userName;
    private String nickName;
    private String sex;
    private String signature;
    private String password;
    public User() {
    }

    public User(String userName, String nickName, String sex, String signature, String password) {
        this.userName = userName;
        this.nickName = nickName;
        this.sex = sex;
        this.signature = signature;
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
