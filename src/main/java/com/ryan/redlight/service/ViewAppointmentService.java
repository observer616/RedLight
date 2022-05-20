package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.entity.ViewAppointment;

import java.util.Date;

/**
 * @author Ryan
 */
public interface ViewAppointmentService {
    PageInfo<ViewAppointment> selectAll(Integer pageNum);

    PageInfo<ViewAppointment> selectAllByCreatorId(Integer pageNum, Integer creatorId);

    MsgDeprecated insertSelective(ViewAppointment record);

    MsgDeprecated updateByPrimaryKeySelective(ViewAppointment record);

    MsgDeprecated insertSelective(Integer houseId, Integer clientId, Date viewTime);
}
