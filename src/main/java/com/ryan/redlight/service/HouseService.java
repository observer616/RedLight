package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;

/**
 * @author Ryan
 */
public interface HouseService {
    PageInfo<House> selectAll(Integer pageNum);

    PageInfo<House> selectList(Integer pageNum, String condition);

    PageInfo<House> selectLike(Integer pageNum, String condition);

    House selectByPrimaryKey(Integer houseId);

    Msg insertSelective(House record);

    Msg updateByPrimaryKeySelective(House record);

    Msg deleteByPrimaryKey(Integer clientId);
}
