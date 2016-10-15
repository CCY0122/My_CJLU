package com.example.diy_indicator.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/10/14.
 */

public class BmobComment extends BmobObject {


    private Integer agreeCount =0;
    private String content;
    private boolean agreeFlag = false;
    private Integer commentCount = 0;

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }


    public boolean isAgreeFlag() {
        return agreeFlag;
    }

    public void setAgreeFlag(boolean agreeFlag) {
        this.agreeFlag = agreeFlag;
    }



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
