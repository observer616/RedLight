package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.entity.Msg;
import com.ryan.redlight.mapper.ClientMapper;
import com.ryan.redlight.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ryan
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientMapper clientMapper;

    @Override
    public Client selectByNickName(String nickName) {
        return clientMapper.selectByNickName(nickName);
    }

    @Override
    public PageInfo<Client> selectAll(Integer pageNum) {
        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Client> list = clientMapper.selectAll();
        //用PageInfo对结果进行包装
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Client> selectLike(Integer pageNum, String condition) {
        //设置待查字段
        Client record = new Client();
        record.setNickName("");

        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Client> list = clientMapper.selectLike(record, condition);
        return new PageInfo<>(list);
    }

    @Override
    public Msg insertSelective(Client record) {
        Client duplicate = clientMapper.selectByNickName(record.getNickName());
        if (duplicate != null) {
            return new Msg(Msg.STATE_FAILURE, "用户名已存在");
        }
        clientMapper.insertSelective(record);
        return new Msg(Msg.STATE_SUCCESS, "注册成功");
    }

    @Override
    public Msg updateByPrimaryKeySelective(Client record) {
        clientMapper.updateByPrimaryKeySelective(record);
        return new Msg(Msg.STATE_SUCCESS);
    }

    @Override
    public Msg deleteByPrimaryKey(Integer clientId) {
        clientMapper.deleteByPrimaryKey(clientId);
        return new Msg(Msg.STATE_SUCCESS);
    }

    @Override
    public PageInfo<Client> selectList(Integer pageNum, String condition) {
        if (condition == null) {
            return selectAll(pageNum);
        } else {
            return selectLike(pageNum, condition);
        }
    }
}
