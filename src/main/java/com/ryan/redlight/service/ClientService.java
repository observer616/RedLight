package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.MsgDeprecated;

/**
 * @author Ryan
 */
public interface ClientService {
    Client selectByNickName(String nickName);

    PageInfo<Client> selectAll(Integer pageNum);

    PageInfo<Client> selectLike(Integer pageNum, String condition);

    PageInfo<Client> selectList(Integer pageNum, String condition);

    MsgDeprecated insertSelective(Client record);

    MsgDeprecated updateByPrimaryKeySelective(Client record);

    MsgDeprecated deleteByPrimaryKey(Integer clientId);

}
