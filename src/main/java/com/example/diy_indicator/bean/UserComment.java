package com.example.diy_indicator.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/10/15.
 */

public class UserComment extends BmobObject{
    private BmobComment bmobComment;
    private String comment;

    public BmobComment getBmobComment() {
        return bmobComment;
    }

    public void setBmobComment(BmobComment bmobComment) {
        this.bmobComment = bmobComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
