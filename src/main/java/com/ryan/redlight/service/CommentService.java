package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.entity.vo.CommentVo;

/**
 * @author Ryan
 */
public interface CommentService {
    PageInfo<CommentVo> selectAllByCreator(Integer pageNum, Client creator);

    PageInfo<CommentVo> selectAll(Integer pageNum);

    Msg insertSelective(Comment record);

    Msg updateByPrimaryKeySelective(Comment record);

}
