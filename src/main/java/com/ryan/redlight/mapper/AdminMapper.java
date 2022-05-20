package com.ryan.redlight.mapper;

import com.ryan.redlight.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    Admin selectByNickName(String nickName);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}