package com.ryan.redlight.service.impl;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.vo.Msg;
import com.ryan.redlight.mapper.AdminMapper;
import com.ryan.redlight.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author Ryan
 */
@Service
public class AdminServiceImpl implements AdminService {
    final
    AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin selectByNickname(String nickname) {
        return adminMapper.selectByNickName(nickname);
    }

    @Override
    public Msg insertSelective(Admin record) {
        Integer adminId = adminMapper.insertSelective(record);
        return new Msg("创建成功", "管理员创建成功");
    }
}
