package com.ryan.redlight.mapper;

import com.ryan.redlight.entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {
    int deleteByPrimaryKey(Integer clientId);

    int insert(Client record);

    int insertSelective(Client record);

    Client selectByPrimaryKey(Integer clientId);

    Client selectByNickname(String nickname);

    List<Client> selectAll();

    List<Client> selectLike(Client client, String condition);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);

}