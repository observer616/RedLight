package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.MsgDeprecated;

/**
 * @author Ryan
 */
public interface CommentService {
    PageInfo<Comment> selectAllByCreatorId(Integer pageNum, Integer creatorId);

    PageInfo<Comment> selectAll(Integer pageNum);

    MsgDeprecated insertSelective(Comment record);

    MsgDeprecated updateByPrimaryKeySelective(Comment record);

}
