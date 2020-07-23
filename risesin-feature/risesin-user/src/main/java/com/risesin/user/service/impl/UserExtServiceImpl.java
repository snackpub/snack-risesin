package com.risesin.user.service.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.user.dao.UserExtDao;
import com.risesin.user.entity.UserExt;
import com.risesin.user.service.IUserExtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * sysUser服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class UserExtServiceImpl extends RisesinBaseServiceImpl<UserExtDao, UserExt, String> implements IUserExtService {

    /**
     * 根据ID查询实体
     */
    @Override
    public UserExt findById(String id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    @Override
    public void add(UserExt user) {
        baseDao.save(user);
    }

    @Override
    public void update(UserExt user) {
        baseDao.update(user, user.getId());
    }

}
