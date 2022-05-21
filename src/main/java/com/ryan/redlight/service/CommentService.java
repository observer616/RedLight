package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.entity.MsgDeprecated;

/**
 * @author Ryan
 */
public interface CommentService {
    PageInfo<Comment> selectAllByCreatorId(Integer pageNum, Integer creatorId);

    PageInfo<Comment> selectAll(Integer pageNum);

    Msg insertSelective(Comment record);

    Msg updateByPrimaryKeySelective(Comment record);

}
