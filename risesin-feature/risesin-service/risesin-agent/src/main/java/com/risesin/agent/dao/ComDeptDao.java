package com.risesin.agent.dao;

import com.risesin.agent.entity.ComDept;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comDept数据访问接口
 *
 * @author Administrator
 */
public interface ComDeptDao extends JpaRepository<ComDept, Integer>, JpaSpecificationExecutor<ComDept>, BaseDao<ComDept, Integer> {

}
