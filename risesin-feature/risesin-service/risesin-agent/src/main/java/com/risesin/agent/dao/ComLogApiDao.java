package com.risesin.agent.dao;

import com.risesin.agent.entity.ComLogApi;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comLogApi数据访问接口
 *
 * @author Administrator
 */
public interface ComLogApiDao extends JpaRepository<ComLogApi, Integer>, JpaSpecificationExecutor<ComLogApi>, BaseDao<ComLogApi, Integer> {

}
