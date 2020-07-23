package com.risesin.system.wrapper.serrating;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.systemapi.serrating.entity.ServiceRating;
import com.risesin.systemapi.serrating.vo.ServiceRatingVO;

/**
 * 视图包装类
 *
 * @author honey
 * @date 2019/12/20 9:49
 */
public class ServiceRatingWrapper extends BaseEntityWrapper<ServiceRating, ServiceRatingVO> {

    public static ServiceRatingWrapper build() {
        return new ServiceRatingWrapper();
    }


    @Override
    public ServiceRatingVO entityVO(ServiceRating entity) {
        return BeanUtil.copy(entity, ServiceRatingVO.class);
    }
}
