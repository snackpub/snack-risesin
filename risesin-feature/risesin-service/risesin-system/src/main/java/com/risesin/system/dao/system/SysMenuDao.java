package com.risesin.system.dao.system;

import com.risesin.core.base.BaseDao;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.systemapi.system.entity.SysMenu;
import com.risesin.systemapi.system.vo.MenuVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * sysMenu数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface SysMenuDao extends BaseDao<SysMenu, Integer> {


    @Query("select m from SysMenu m where id=:id and m.status=0")
    SysMenu detail(@Param("id") Integer id);


    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update SysMenu u set u.status=2 where u.id in (:coll)")
    int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

    /**
     * 所有菜单
     *
     * @return
     */
    @Query(value = "select * from t_sys_menu m where and m.category = 1", nativeQuery = true)
    List<SysMenu> allMenu();


    /**
     * 授权树形结构
     *
     * @return
     */
    @Query("select new com.risesin.systemapi.system.vo.MenuVO(m.id, m.parentId, m.name, m.alias) from SysMenu m where m.status = 0")
    List<MenuVO> grantTree();

    /**
     * 角色已配置菜单
     *
     * @param roleId
     * @return
     */
    @Query(value = " select * from t_sys_menu m where m.status = 0 and m.pk_id IN " +
            "(SELECT rm.menu_id FROM t_sys_menu_role rm WHERE rm.role_id IN (:coll))", nativeQuery = true)
    List<SysMenu> roleMenu(@Param("coll") List<Integer> roleId);


    /**
     * 菜单树形结构
     *
     * @return
     */
    @Query(value = "select new com.risesin.systemapi.system.vo.MenuVO(m.id, m.parentId, m.name, m.alias, m.path, m.source, m.sort, m.category,m.isOpen,m.remark) from  SysMenu m where m.status=0")
    List<MenuVO> tree();

}
