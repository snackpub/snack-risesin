package com.risesin.core.log.service;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.log.model.LogUsual;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 服务类
 *
 * @author honey
 * @since 2019-12-1
 */
public interface ILogUsualService extends RisesinBaseService<LogUsual, Integer> {

    @Override
    LogUsual findById(Integer integer);

    @Override
    void add(LogUsual logUsual);

    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页
     * @return Page<SysLoanAgency>
     */
    Page<LogUsual> findSearch(Map whereMap, Query query);
}
