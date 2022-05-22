package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Comment;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.entity.vo.CommentVo;
import com.ryan.redlight.mapper.AdminMapper;
import com.ryan.redlight.mapper.ClientMapper;
import com.ryan.redlight.mapper.CommentMapper;
import com.ryan.redlight.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan
 */
@Service
public class CommentServiceImpl implements CommentService {
    final
    CommentMapper commentMapper;

    final
    ClientMapper clientMapper;

    final
    AdminMapper adminMapper;

    public CommentServiceImpl(CommentMapper commentMapper, ClientMapper clientMapper, AdminMapper adminMapper) {
        this.commentMapper = commentMapper;
        this.clientMapper = clientMapper;
        this.adminMapper = adminMapper;
    }

    @Override
    public PageInfo<CommentVo> selectAllByCreator(Integer pageNum, Client creator) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Comment> comments = commentMapper.selectAllByCreatorId(creator.getClientId());
        // 组装clientVo
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getIsReplied() == 0) {
                commentVos.add(new CommentVo(comment, creator));
            } else {
                Admin admin = adminMapper.selectByPrimaryKey(comment.getReplyId());
                commentVos.add(new CommentVo(comment, creator, admin));
            }
        }
        //用PageInfo对结果进行包装
        return new PageInfo<>(commentVos, PageConfig.PAGE_SIZE);
    }

    @Override
    public PageInfo<CommentVo> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Comment> comments = commentMapper.selectALL();
        // TODO: 2022/5/22  optimize
        System.out.println(comments);
        List<CommentVo> commentVos = new ArrayList<>();
        for (Comment comment : comments) {
            Client creator = clientMapper.selectByPrimaryKey(comment.getCreatorId());
            // OPTIMIZE: 2022/5/22 clientMapper.selectNicknameByPrimaryKey(Integer);
            System.out.println(creator);
            if (comment.getIsReplied() == 0) {
                commentVos.add(new CommentVo(comment, creator));
            } else {
                Admin admin = adminMapper.selectByPrimaryKey(comment.getReplyId());
                commentVos.add(new CommentVo(comment, creator, admin));
            }
        }
        return new PageInfo<>(commentVos, PageConfig.PAGE_SIZE);
    }

    @Override
    public Msg insertSelective(Comment record) {
        int commentId = commentMapper.insertSelective(record);
        return new Msg("留言成功", "留言成功");
    }

    @Override
    public Msg updateByPrimaryKeySelective(Comment record) {
        int affectRow = commentMapper.updateByPrimaryKeySelective(record);
        return new Msg("回复成功", "回复成功");
    }


}
