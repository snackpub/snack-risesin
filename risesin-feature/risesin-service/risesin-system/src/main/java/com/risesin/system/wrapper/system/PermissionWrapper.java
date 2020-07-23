package com.risesin.system.wrapper.system;

import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.dict.DictServiceImpl;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.systemapi.system.entity.SysPermission;
import com.risesin.systemapi.system.vo.SysPermissionVO;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author risesin
 * @date 2019-12-19
 */
public class PermissionWrapper extends BaseEntityWrapper<SysPermission, SysPermissionVO> {

    private static DictServiceImpl dictService;

    static {
        dictService = SpringUtil.getBean(DictServiceImpl.class);
    }

    public static PermissionWrapper build() {
        return new PermissionWrapper();
    }


    @Override
    public SysPermissionVO entityVO(SysPermission value) {
        SysPermissionVO tSysPermissionVO = BeanUtil.copy(value, SysPermissionVO.class);
        return tSysPermissionVO;
    }
}
