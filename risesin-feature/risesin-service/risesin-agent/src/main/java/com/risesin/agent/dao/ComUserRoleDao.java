package com.risesin.agent.dao;

import com.risesin.agent.entity.ComUserRole;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comUserRole数据访问接口
 *
 * @author Administrator
 */
public interface ComUserRoleDao extends JpaRepository<ComUserRole, Integer>, JpaSpecificationExecutor<ComUserRole>, BaseDao<ComUserRole, Integer> {

}
