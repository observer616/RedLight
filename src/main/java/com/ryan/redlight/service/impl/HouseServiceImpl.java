package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.mapper.HouseMapper;
import com.ryan.redlight.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ryan
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseMapper houseMapper;

    @Override
    public PageInfo<House> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<House> list = houseMapper.selectAll();
        //用PageInfo对结果进行包装
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<House> selectList(Integer pageNum, String condition) {
        if (condition == null) {
            return selectAll(pageNum);
        } else {
            return selectLike(pageNum, condition);
        }
    }

    @Override
    public PageInfo<House> selectLike(Integer pageNum, String condition) {
        //设置待查字段
        House record = new House();
        record.setAddress("");
        record.setDivision("");
        record.setName("wow");
        record.setType("");

        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<House> list = houseMapper.selectLike(record, condition);
        return new PageInfo<>(list);
    }

    @Override
    public House selectByPrimaryKey(Integer houseId) {
        House house = houseMapper.selectByPrimaryKey(houseId);
        if (house != null) {
            house.setClick(house.getClick() + 1);
            updateByPrimaryKeySelective(house);
        }
        return house;
    }

    @Override
    public MsgDeprecated insertSelective(House record) {
        houseMapper.insertSelective(record);
        return new MsgDeprecated(MsgDeprecated.STATE_SUCCESS);
    }

    @Override
    public MsgDeprecated updateByPrimaryKeySelective(House record) {
        houseMapper.updateByPrimaryKeySelective(record);
        return new MsgDeprecated(MsgDeprecated.STATE_SUCCESS);
    }

    @Override
    public MsgDeprecated deleteByPrimaryKey(Integer houseId) {
        houseMapper.deleteByPrimaryKey(houseId);
        return new MsgDeprecated(MsgDeprecated.STATE_SUCCESS);
    }
}
