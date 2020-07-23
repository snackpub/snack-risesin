package com.risesin.system.service.system;

import java.util.List;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.system.entity.SysLoanAgency;
import com.risesin.systemapi.system.entity.SysPermission;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * SysPermission的服务接口
 *
 * @author
 * @date
 */
public interface ISysPermissionService extends RisesinBaseService<SysPermission, Integer> {


    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页对象
     * @return Page<SysPermission>
     */
    Page<SysPermission> findSearch(Map whereMap, Query query);

    void update(SysPermission sysPermission);
}
