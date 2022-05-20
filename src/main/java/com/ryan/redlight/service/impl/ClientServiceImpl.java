package com.ryan.redlight.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryan.redlight.config.PageConfig;
import com.ryan.redlight.entity.Client;
import com.ryan.redlight.mapper.ClientMapper;
import com.ryan.redlight.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ryan
 */
@Service
public class ClientServiceImpl implements ClientService {
    final
    ClientMapper clientMapper;

    public ClientServiceImpl(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public Client selectByNickName(String nickname) {
        return clientMapper.selectByNickname(nickname);
    }

    @Override
    public PageInfo<Client> selectList(Integer pageNum, String condition) {
        if (condition == null) {
            return selectAll(pageNum);
        } else {
            return selectLike(pageNum, condition);
        }
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
        record.setNickname("");

        PageHelper.startPage(pageNum, PageConfig.PAGE_SIZE);
        List<Client> list = clientMapper.selectLike(record, condition);
        return new PageInfo<>(list);
    }

    @Override
    public Integer insertSelective(Client record) {
        Client duplicate = clientMapper.selectByNickname(record.getNickname());
        if (duplicate != null) {
            return null;
        }
        return clientMapper.insertSelective(record);
    }

    @Override
    public Boolean updateByPrimaryKeySelective(Client record) {
        int affectRow = clientMapper.updateByPrimaryKeySelective(record);
        return affectRow != 0;
    }

    @Override
    public Boolean deleteByPrimaryKey(Integer clientId) {
        int affectRow = clientMapper.deleteByPrimaryKey(clientId);
        return affectRow != 0;
    }

}
