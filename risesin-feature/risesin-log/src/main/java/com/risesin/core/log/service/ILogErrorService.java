package com.risesin.core.log.service;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.model.LogError;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 服务类
 *
 * @author honey
 * @since 2019-12-1
 */
public interface ILogErrorService extends RisesinBaseService<LogError, Integer> {
    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页
     * @return Page<SysLoanAgency>
     */
    Page<LogError> findSearch(Map whereMap, Query query);

    @Override
    void add(LogError var1);
}
