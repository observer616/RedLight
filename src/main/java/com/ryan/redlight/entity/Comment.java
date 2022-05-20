package com.ryan.redlight.entity;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer creatorId;

    private Date createTime;

    private String info;

    private Byte isReplied;

    private Integer replyId;

    private Date replyTime;

    private String replyInfo;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Byte getIsReplied() {
        return isReplied;
    }

    public void setIsReplied(Byte isReplied) {
        this.isReplied = isReplied;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo == null ? null : replyInfo.trim();
    }
}