package com.ryan.redlight.mapper;

import com.ryan.redlight.entity.House;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {
    int deleteByPrimaryKey(Integer houseId);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer houseId);

    List<House> selectLike(House record, String condition);

    List<House> selectAll();

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
}