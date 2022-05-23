package com.ryan.redlight.entity.vo;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Comment;

/**
 * 留言VO对象，包含用户名、管理员名
 *
 * @author Ryan
 */
public class CommentVo extends Comment {
    private String creatorName;
    private String replyName;

    public CommentVo() {
        super();
    }

    public CommentVo(Comment comment, Client client) {
        this();
        this.setCommentId(comment.getCommentId());
        this.setCreatorId(comment.getCreatorId());
        this.setCreateTime(comment.getCreateTime());
        this.setInfo(comment.getInfo());
        this.setIsReplied(comment.getIsReplied());
        this.setReplyId(comment.getReplyId());
        this.setReplyTime(comment.getReplyTime());
        this.setReplyInfo(comment.getReplyInfo());

        // 客户名
        creatorName = client.getNickname();
        // 无回复
        replyName = null;
    }

    public CommentVo(Comment comment, Client client, Admin admin) {
        this();
        this.setCommentId(comment.getCommentId());
        this.setCreatorId(comment.getCreatorId());
        this.setCreateTime(comment.getCreateTime());
        this.setInfo(comment.getInfo());
        this.setIsReplied(comment.getIsReplied());
        this.setReplyId(comment.getReplyId());
        this.setReplyTime(comment.getReplyTime());
        this.setReplyInfo(comment.getReplyInfo());

        // 创建者 客户名
        creatorName = client.getNickname();
        // 回复者 管理员名
        replyName = admin.getNickname();
    }

    @Override
    public String toString() {
        return "CommentVo{" + super.toString() +
                "creatorName='" + creatorName + '\'' +
                ", replyName='" + replyName + '\'' +
                '}';
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

}
