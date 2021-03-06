package com.ryan.redlight.mapper;

import com.ryan.redlight.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int deleteByCreatorId(Integer creatorId);

    int deleteByReplierId(Integer replierId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAllByCreatorId(Integer creatorId);

    List<Comment> selectALL();
}