package com.risesin.core.log.dao;

import com.risesin.core.base.BaseDao;
import com.risesin.core.log.model.LogUsual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * sysLogUsual数据访问接口
 *
 * @author Administrator
 */
public interface SysLogUsualDao extends BaseDao<LogUsual, Integer> {

}
