package com.risesin.agent.dao;

import com.risesin.agent.entity.ComRoleMenu;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comRoleMenu数据访问接口
 *
 * @author Administrator
 */
public interface ComRoleMenuDao extends JpaRepository<ComRoleMenu, Integer>, JpaSpecificationExecutor<ComRoleMenu>, BaseDao<ComRoleMenu, Integer> {

}
