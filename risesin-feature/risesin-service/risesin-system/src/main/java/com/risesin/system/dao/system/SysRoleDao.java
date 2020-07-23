package com.risesin.system.dao.system;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.system.entity.SysRole;
import com.risesin.systemapi.system.vo.RoleVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * sysRole数据访问接口
 *
 * @author Administrator
 */
public interface SysRoleDao extends BaseDao<SysRole, Integer> {


    @Query("select m from SysRole m where id=:id and m.status=0")
    SysRole detail(@Param("id") Integer id);

    /**
     * 部门tree查询
     *
     * @return
     */
    @Query(value = "select new com.risesin.systemapi.system.vo.RoleVO(r.id,r.parentId,r.roleAlias,r.roleName,r.status,r.sort,r.createTime,r.createUser) from  SysRole r where r.status = 0")
    List<RoleVO> tree();

}
