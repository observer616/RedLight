package com.ryan.redlight.service;

import com.github.pagehelper.PageInfo;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.entity.ViewAppointment;

import java.util.Date;

/**
 * @author Ryan
 */
public interface ViewAppointmentService {
    PageInfo<ViewAppointment> selectAll(Integer pageNum);

    PageInfo<ViewAppointment> selectAllByCreatorId(Integer pageNum, Integer creatorId);

    Msg insertSelective(ViewAppointment record);

    Msg updateByPrimaryKeySelective(ViewAppointment record);

    Msg insertSelective(Integer houseId, Integer clientId, Date viewTime);
}
