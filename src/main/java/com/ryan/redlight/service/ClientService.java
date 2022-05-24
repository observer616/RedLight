package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.vo.Msg;

/**
 * @author Ryan
 */
public interface ClientService {
    Client selectByNickname(String nickname);

    PageInfo<Client> selectAll(Integer pageNum);

    PageInfo<Client> selectLike(Integer pageNum, String condition);

    PageInfo<Client> selectList(Integer pageNum, String condition);

    /**
     * record.clientId==null,
     * record.nickName!=null,
     * record.password!=null,
     *
     * @param record client
     * @return clientId
     */
    Msg insertSelective(Client record);

    /**
     * @param record client
     * @return row affected
     */
    Msg updateByPrimaryKeySelective(Client record);

    /**
     * @param clientId clientId
     * @return row affected
     */
    Msg deleteByPrimaryKey(Integer clientId);

    Client selectByPrimaryKey(Integer clientId);
}
