package com.ryan.redlight.service;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.MsgDeprecated;

/**
 * @author Ryan
 */
public interface AdminService {
    Admin selectByNickName(String nickName);

    MsgDeprecated insertSelective(Admin record);
}
