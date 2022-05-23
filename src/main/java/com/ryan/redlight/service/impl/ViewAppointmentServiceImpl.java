package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.House;
import com.ryan.redlight.entity.ViewAppointment;
import com.ryan.redlight.entity.vo.AppointmentVo;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.mapper.AdminMapper;
import com.ryan.redlight.mapper.ClientMapper;
import com.ryan.redlight.mapper.HouseMapper;
import com.ryan.redlight.mapper.ViewAppointmentMapper;
import com.ryan.redlight.service.ViewAppointmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 */
@Service
public class ViewAppointmentServiceImpl implements ViewAppointmentService {
    final
    HouseMapper houseMapper;
    final
    ViewAppointmentMapper viewAppointmentMapper;

    final
    ClientMapper clientMapper;

    final
    AdminMapper adminMapper;

    public ViewAppointmentServiceImpl(ViewAppointmentMapper viewAppointmentMapper,
                                      HouseMapper houseMapper,
                                      ClientMapper clientMapper,
                                      AdminMapper adminMapper) {
        this.viewAppointmentMapper = viewAppointmentMapper;
        this.clientMapper = clientMapper;
        this.adminMapper = adminMapper;
        this.houseMapper = houseMapper;
    }

    public List<AppointmentVo> toAppointmentVoList(List<ViewAppointment> appointments) {
        List<AppointmentVo> list = new ArrayList<>();
        for (ViewAppointment appointment : appointments) {
            House house = houseMapper.selectByPrimaryKey(appointment.getHouseId());
            Client creator = clientMapper.selectByPrimaryKey(appointment.getCreatorId());
            if (appointment.getIsReplied() == 0) {
                list.add(new AppointmentVo(appointment, house, creator));
            } else {
                Admin admin = adminMapper.selectByPrimaryKey(appointment.getReplyerId());
                list.add(new AppointmentVo(appointment, house, creator, admin));
            }
        }
        return list;
    }

    @Override
    public PageInfo<AppointmentVo> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<ViewAppointment> appointments = viewAppointmentMapper.selectAll();
        List<AppointmentVo> list = toAppointmentVoList(appointments);
        //用PageInfo对结果进行包装
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<AppointmentVo> selectAllByCreatorId(Integer pageNum, Integer creatorId) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<ViewAppointment> appointments = viewAppointmentMapper.selectAllByCreatorId(creatorId);
        List<AppointmentVo> list = toAppointmentVoList(appointments);
        //用PageInfo对结果进行包装
        return new PageInfo<>(list);
    }

    @Override
    public Msg insertSelective(ViewAppointment record) {
        viewAppointmentMapper.insertSelective(record);
        return new Msg("预约成功", "预约成功");
    }

    @Override
    public Msg updateByPrimaryKeySelective(ViewAppointment record) {
        viewAppointmentMapper.updateByPrimaryKeySelective(record);
        return new Msg("更新成功", "更新成功");
    }

    @Override
    public Msg insertSelective(Integer houseId, Integer clientId, Date viewTime) {
        ViewAppointment viewAppointment = new ViewAppointment();
        viewAppointment.setHouseId(houseId);
        viewAppointment.setCreatorId(clientId);
        viewAppointment.setViewTime(viewTime);
        return insertSelective(viewAppointment);
    }
}
