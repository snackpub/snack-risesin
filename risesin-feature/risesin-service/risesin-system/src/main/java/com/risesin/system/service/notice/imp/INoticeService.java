package com.risesin.system.service.notice.imp;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.notice.entity.SysNotice;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author honey
 */
public interface INoticeService extends RisesinBaseService<SysNotice, Integer> {

    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页
     * @return Page<SysLoanAgency>
     */
    Page<SysNotice> findSearch(Map whereMap, Query query);

    boolean removeBatch(List<Integer> ids);

    void update(SysNotice notice);

}
