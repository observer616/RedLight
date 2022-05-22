package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.vo.Msg;
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
    public Msg insertSelective(House record) {
        int houseId = houseMapper.insertSelective(record);
        return new Msg("创建成功");
    }

    @Override
    public Msg updateByPrimaryKeySelective(House record) {
        int affectRow = houseMapper.updateByPrimaryKeySelective(record);
        return new Msg("更改成功");
    }

    @Override
    public Msg deleteByPrimaryKey(Integer houseId) {
        int affectRow = houseMapper.deleteByPrimaryKey(houseId);
        return new Msg("删除成功");
    }
}
