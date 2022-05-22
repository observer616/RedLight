package com.ryan.redlight.entity.vo;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Comment;

import java.util.Date;

/**
 * @author Ryan
 */
public class CommentVo {
    private Integer commentId;
    private Integer creatorId;
    private String creatorName;
    private Date createTime;
    private String info;
    private Byte isReplied;
    private Integer replyId;
    private String replyName;
    private Date replyTime;
    private String replyInfo;

    public CommentVo() {

    }

    public CommentVo(Comment comment, Client client) {
        this();
        commentId = comment.getCommentId();
        creatorId = comment.getCreatorId();
        // 用户
        creatorName = client.getNickname();
        createTime = comment.getCreateTime();
        info = comment.getInfo();
        isReplied = comment.getIsReplied();
        replyId = comment.getReplyId();
        // 无回复
        replyName = null;
        replyTime = comment.getReplyTime();
        replyInfo = comment.getReplyInfo();
    }

    public CommentVo(Comment comment, Client client, Admin admin) {
        this();
        commentId = comment.getCommentId();
        creatorId = comment.getCreatorId();
        // 客户名
        creatorName = client.getNickname();
        createTime = comment.getCreateTime();
        info = comment.getInfo();
        isReplied = comment.getIsReplied();
        replyId = comment.getReplyId();
        // 管理员名
        replyName = admin.getNickName();
        replyTime = comment.getReplyTime();
        replyInfo = comment.getReplyInfo();
    }

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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
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
        this.info = info;
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
        this.replyInfo = replyInfo;
    }
}
