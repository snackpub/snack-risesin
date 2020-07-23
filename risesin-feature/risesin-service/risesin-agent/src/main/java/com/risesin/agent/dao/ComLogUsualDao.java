package com.risesin.agent.dao;

import com.risesin.agent.entity.ComLogUsual;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comLogUsual数据访问接口
 *
 * @author Administrator
 */
public interface ComLogUsualDao extends JpaRepository<ComLogUsual, Integer>, JpaSpecificationExecutor<ComLogUsual>, BaseDao<ComLogUsual, Integer> {

}
