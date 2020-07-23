package com.risesin.system.service.flowlog;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.flowlog.entity.UserLog;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 企业用户操作日志管理的服务接口
 *
 * @author honey
 * @date 2019-12-22
 */
public interface UserLogService extends RisesinBaseService<UserLog, Integer> {


    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页对象
     * @return Page<UserLog>
     */
    Page<UserLog> findSearch(Map whereMap, Query query);

    /**
     * 修改对象
     *
     * @param userLog 对象
     */
    void update(UserLog userLog);
}
