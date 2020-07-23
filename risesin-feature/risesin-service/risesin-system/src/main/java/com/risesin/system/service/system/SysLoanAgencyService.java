package com.risesin.system.service.system;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.system.entity.SysLoanAgency;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 机构管理服务类
 *
 * @author honey
 * @date 2019/12/11 20:50
 */
public interface SysLoanAgencyService extends RisesinBaseService<SysLoanAgency, Integer> {

    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页
     * @return Page<SysLoanAgency>
     */
    Page<SysLoanAgency> findSearch(Map whereMap, Query query);


    void update(SysLoanAgency sysLoanAgency);
}
