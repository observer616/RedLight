package com.ryan.redlight.service;

import com.ryan.redlight.entity.Admin;
import com.ryan.redlight.entity.vo.Msg;

/**
 * @author Ryan
 */
public interface AdminService {
    Admin selectByNickname(String nickname);

    Msg insertSelective(Admin record);
}
