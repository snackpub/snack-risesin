package com.risesin.user.service;


import com.risesin.core.base.RisesinBaseService;
import com.risesin.user.entity.UserExt;

/**
 * @author Administrator
 */
public interface IUserExtService extends RisesinBaseService<UserExt, String> {


    /**
     * 更新用户
     *
     * @param userExt
     */
    void update(UserExt userExt);

}
