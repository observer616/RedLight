package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.mapper.CommentMapper;
import com.ryan.redlight.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ryan
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public PageInfo<Comment> selectAllByCreatorId(Integer pageNum, Integer creatorId) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Comment> comments = commentMapper.selectAllByCreatorId(creatorId);
        //用PageInfo对结果进行包装
        return new PageInfo<>(comments);
    }

    @Override
    public MsgDeprecated insertSelective(Comment record) {
        return null;
    }

    @Override
    public MsgDeprecated updateByPrimaryKeySelective(Comment record) {
        return null;
    }

    @Override
    public PageInfo<Comment> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Comment> comments = commentMapper.selectALL();
        return new PageInfo<>(comments);
    }
}
