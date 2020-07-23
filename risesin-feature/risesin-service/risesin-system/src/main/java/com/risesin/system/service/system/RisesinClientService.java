package com.risesin.system.service.system;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.system.entity.RisesinClient;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * RisesinClient的服务接口
 *
 * @author honey
 * @date 2019-12-22
 */
public interface RisesinClientService extends RisesinBaseService<RisesinClient, Integer> {


    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页对象
     * @return Page<RisesinClient>
     */
    Page<RisesinClient> findSearch(Map whereMap, Query query);

    /**
     * 修改对象
     *
     * @param risesinClient 对象
     */
    void update(RisesinClient risesinClient);
}
