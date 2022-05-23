package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.Client;

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
    Integer insertSelective(Client record);

    /**
     * @param record client
     * @return row affected
     */
    Boolean updateByPrimaryKeySelective(Client record);

    /**
     * @param clientId clientId
     * @return row affected
     */
    Boolean deleteByPrimaryKey(Integer clientId);

}
