package com.risesin.agent.dao;

import com.risesin.agent.entity.ComPermission;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comPermission数据访问接口
 *
 * @author Administrator
 */
public interface ComPermissionDao extends JpaRepository<ComPermission, Integer>, JpaSpecificationExecutor<ComPermission>, BaseDao<ComPermission, Integer> {

}
