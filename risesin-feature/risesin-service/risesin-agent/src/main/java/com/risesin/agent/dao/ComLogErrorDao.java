package com.risesin.agent.dao;

import com.risesin.agent.entity.ComLogError;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comLogError数据访问接口
 *
 * @author Administrator
 */
public interface ComLogErrorDao extends JpaRepository<ComLogError, Integer>, JpaSpecificationExecutor<ComLogError>, BaseDao<ComLogError, Integer> {

}
