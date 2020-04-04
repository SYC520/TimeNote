/*
 *    项目名称:TimeNote
 *    文件名称:NoteGroup.java
 *    Date:4/3/20 4:10 PM
 *    Author:SYC
 *    Copyright(c) 2020, SYC
 */

package com.syc.timenote.bean;

public class NoteGroup {

    private int id;
    private String name;

    public NoteGroup() {
    }

    public NoteGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
