/*
 *    项目名称:TimeNote
 *    文件名称:Note.java
 *    Date:4/3/20 4:01 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.bean;

public class Note {

    private int id;         //笔记ID
    private String content; //笔记内容
    private String groupName;    //笔记所属组的名字
    private String createTime; //笔记创建时间
    private String title;  //   这里的title 指的是  笔记的第一行 一般都是纲要 用于显示纲要
    private String subContent; //这里的subContent指的是 笔记的第二行，用于反应除了用户的开头   相当于内容的缩写

    public Note() {
    }

    public Note(int id, String content, String groupName, String createTime, String title, String subContent) {
        this.id = id;
        this.content = content;
        this.groupName = groupName;
        this.createTime = createTime;
        this.title = title;
        this.subContent = subContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }
}
