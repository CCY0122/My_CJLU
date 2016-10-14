package com.example.diy_indicator.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/10/14.
 */

public class BmobComment extends BmobObject {


    private Integer agreeCount =0;
    private String content;

    public boolean isAgreeFlag() {
        return agreeFlag;
    }

    public void setAgreeFlag(boolean agreeFlag) {
        this.agreeFlag = agreeFlag;
    }

    private boolean agreeFlag = false;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }
}
