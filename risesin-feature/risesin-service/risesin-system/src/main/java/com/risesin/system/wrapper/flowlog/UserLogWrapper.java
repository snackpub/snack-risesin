package com.risesin.system.wrapper.flowlog;

import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;

import com.risesin.systemapi.flowlog.entity.UserLog;
import com.risesin.systemapi.flowlog.vo.UserLogVO;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 * @date 2019-12-22
 */
public class UserLogWrapper extends BaseEntityWrapper<UserLog, UserLogVO> {

    private static DictServiceImpl dictService;

    static {
        dictService = SpringUtil.getBean(DictServiceImpl.class);
    }

    public static UserLogWrapper build() {
        return new UserLogWrapper();
    }


    @Override
    public UserLogVO entityVO(UserLog value) {
        return BeanUtil.copy(value, UserLogVO.class);
    }
}
