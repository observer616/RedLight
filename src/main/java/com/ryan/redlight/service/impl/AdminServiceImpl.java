package com.ryan.redlight.service.impl;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.MsgDeprecated;
import com.ryan.redlight.mapper.AdminMapper;
import com.ryan.redlight.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ryan
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin selectByNickName(String nickName) {
        return adminMapper.selectByNickName(nickName);
    }

    @Override
    public MsgDeprecated insertSelective(Admin record) {
        adminMapper.insertSelective(record);
        return new MsgDeprecated(MsgDeprecated.STATE_SUCCESS);
    }
}
