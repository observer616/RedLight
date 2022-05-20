package com.ryan.redlight.service;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.Msg;

/**
 * @author Ryan
 */
public interface AdminService {
    Admin selectByNickName(String nickName);

    Msg insertSelective(Admin record);
}
