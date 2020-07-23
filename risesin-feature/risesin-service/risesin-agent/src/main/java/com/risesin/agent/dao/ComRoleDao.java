package com.risesin.agent.dao;

import com.risesin.agent.entity.ComRole;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comRole数据访问接口
 *
 * @author Administrator
 */
public interface ComRoleDao extends JpaRepository<ComRole, Integer>, JpaSpecificationExecutor<ComRole>, BaseDao<ComRole, Integer> {

}
