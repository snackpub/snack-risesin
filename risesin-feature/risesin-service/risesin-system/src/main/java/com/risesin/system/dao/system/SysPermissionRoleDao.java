package com.risesin.system.dao.system;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.system.entity.SysPermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * sysPermissionRole数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface SysPermissionRoleDao extends BaseDao<SysPermissionRole, Integer> {

    /**
     * 根据角色ID删除
     *
     * @param ids
     * @return
     */
    @Transactional
    @Modifying
    @Query("delete from SysPermissionRole pr where pr.roleId in (?1)")
    int removeByRoleId(Collection<? extends Serializable> ids);

}
