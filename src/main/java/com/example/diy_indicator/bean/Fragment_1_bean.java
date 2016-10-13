package com.example.diy_indicator.bean;

import java.io.Serializable;

/**
 * pos代表已做或未做的标志，用1或0代表  其实用boolean才对，而且取名不好
 * Created by Administrator on 2016/10/12.
 */

public class Fragment_1_bean implements Serializable{
    private int pos ;
    private String grade;
    private String name;
    private String content;

    public Fragment_1_bean(String grade, String name, String content) {
        this.grade = grade;
        this.name = name;
        this.content = content;
        this.pos=0;
    }
    public Fragment_1_bean(String grade, String name, String content,int pos) {
        this.grade = grade;
        this.name = name;
        this.content = content;
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fragment_1_bean{" +
                "pos=" + pos +
                ", grade='" + grade + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
