package com.risesin.agent.dao;

import com.risesin.agent.entity.ComOrderCheckResult;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comOrderCheckResult数据访问接口
 *
 * @author Administrator
 */
public interface ComOrderCheckResultDao extends JpaRepository<ComOrderCheckResult, Integer>, JpaSpecificationExecutor<ComOrderCheckResult>, BaseDao<ComOrderCheckResult, Integer> {

}
