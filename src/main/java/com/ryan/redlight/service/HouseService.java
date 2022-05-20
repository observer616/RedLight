package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.MsgDeprecated;

/**
 * @author Ryan
 */
public interface HouseService {
    PageInfo<House> selectAll(Integer pageNum);

    PageInfo<House> selectList(Integer pageNum, String condition);

    PageInfo<House> selectLike(Integer pageNum, String condition);

    House selectByPrimaryKey(Integer houseId);

    MsgDeprecated insertSelective(House record);

    MsgDeprecated updateByPrimaryKeySelective(House record);

    MsgDeprecated deleteByPrimaryKey(Integer clientId);
}
