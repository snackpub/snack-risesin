package com.risesin.agent.dao;

import com.risesin.agent.entity.ComPermissionRole;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comPermissionRole数据访问接口
 *
 * @author Administrator
 */
public interface ComPermissionRoleDao extends JpaRepository<ComPermissionRole, Integer>, JpaSpecificationExecutor<ComPermissionRole>, BaseDao<ComPermissionRole, Integer> {

}
