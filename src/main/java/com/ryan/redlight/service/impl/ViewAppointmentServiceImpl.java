package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.mapper.ViewAppointmentMapper;
import com.ryan.redlight.service.ViewAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 */
@Service
public class ViewAppointmentServiceImpl implements ViewAppointmentService {
    @Autowired
    ViewAppointmentMapper viewAppointmentMapper;

    @Override
    public PageInfo<ViewAppointment> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<ViewAppointment> list = viewAppointmentMapper.selectAll();
        //用PageInfo对结果进行包装
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<ViewAppointment> selectAllByCreatorId(Integer pageNum, Integer creatorId) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<ViewAppointment> list = viewAppointmentMapper.selectAllByCreatorId(creatorId);
        //用PageInfo对结果进行包装
        return new PageInfo<>(list);
    }

    @Override
    public MsgDeprecated insertSelective(ViewAppointment record) {
        viewAppointmentMapper.insertSelective(record);
        return new MsgDeprecated(MsgDeprecated.STATE_SUCCESS);
    }

    @Override
    public MsgDeprecated updateByPrimaryKeySelective(ViewAppointment record) {
        viewAppointmentMapper.updateByPrimaryKeySelective(record);
        return new MsgDeprecated(MsgDeprecated.STATE_SUCCESS);
    }

    @Override
    public MsgDeprecated insertSelective(Integer houseId, Integer clientId, Date viewTime) {
        ViewAppointment viewAppointment = new ViewAppointment();
        viewAppointment.setHouseId(houseId);
        viewAppointment.setCreatorId(clientId);
        viewAppointment.setViewTime(viewTime);
        return insertSelective(viewAppointment);
    }
}
