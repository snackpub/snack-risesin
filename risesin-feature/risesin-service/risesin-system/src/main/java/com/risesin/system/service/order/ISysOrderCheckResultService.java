package com.risesin.system.service.order;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.order.entity.SysOrderCheckResult;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 订单检查结果服务
 *
 * @author honey
 */
public interface ISysOrderCheckResultService extends RisesinBaseService<SysOrderCheckResult, String> {


    /**
     * 分页
     *
     * @param whereMap 参数
     * @param query    查询对象
     * @return Page<SysOrderCheckResult>
     */
    Page<SysOrderCheckResult> findSearch(Map whereMap, Query query);
}
