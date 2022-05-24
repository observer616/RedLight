package com.ryan.redlight.mapper;

import com.ryan.redlight.entity.ViewAppointment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewAppointmentMapper {
    int deleteByPrimaryKey(Integer viewAppointmentId);

    int deleteByHouseId(Integer houseId);

    int deleteByCreatorId(Integer creatorId);

    int deleteByReplierId(Integer replierId);

    int insert(ViewAppointment record);

    int insertSelective(ViewAppointment record);

    ViewAppointment selectByPrimaryKey(Integer viewAppointmentId);

    List<ViewAppointment> selectAll();

    List<ViewAppointment> selectAllByCreatorId(Integer creatorId);

    int updateByPrimaryKeySelective(ViewAppointment record);

    int updateByPrimaryKey(ViewAppointment record);

}