package com.risesin.system.service.serrating;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.serrating.entity.ServiceRating;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * ServiceRating的服务接口
 *
 * @author honey
 * @date 2019/12/19
 */
public interface IServiceRatingService extends RisesinBaseService<ServiceRating, Integer> {


    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页对象
     * @return Page<ServiceRating>
     */
    Page<ServiceRating> findSearch(Map whereMap, Query query);

    /**
     * 修改对象
     *
     * @param serviceRating 对象
     */
    void update(ServiceRating serviceRating);
}
