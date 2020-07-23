package com.risesin.user.dao;

import com.risesin.core.base.BaseDao;
import com.risesin.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * sysUser数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User>, BaseDao<User, String> {

    /**
     * 获取角色名
     *
     * @param roleIds 角色ID集合
     * @param status  状态
     * @return list
     */
    @Query(value = "SELECT role_name FROM t_sys_role WHERE pk_id IN (:roleIds) AND status=:status", nativeQuery = true)
    List<String> getRoleName(@Param("roleIds") String[] roleIds, @Param("status") Byte status);

    /**
     * 获取角色别名
     *
     * @param ids id集合
     * @return ${List}
     */
    @Query(value = "SELECT role_alias FROM t_sys_role WHERE pk_id IN (:ids)", nativeQuery = true)
    List<String> getRoleAlias(String[] ids);

    /**
     * 获取部门名
     *
     * @param ids    部门集合
     * @param status 状态
     * @return list
     */
    @Query(value = "SELECT dept_name FROM t_sys_dept WHERE pk_id IN (:ids) AND status=:status", nativeQuery = true)
    List<String> getDeptName(String[] ids, Byte status);

    /**
     * 获取用户名称
     *
     * @param id 主键id
     * @return ${String}
     */
    @Query(value = "SELECT user_name FROM risesin_user where pk_id = :id", nativeQuery = true)
    String getUserName(@Param("id") String id);

    /**
     * 给用户设置角色-> 批量
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     * @return ${boolean}
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "UPDATE risesin_user u SET u.role_id=:roleIds WHERE u.pk_id=:userId", nativeQuery = true)
    int grantUserByRole(@Param("userId") String userId, @Param("roleIds") String roleIds);

    /**
     * 获取用户
     *
     * @param account 账户
     * @param phone   手机号
     * @return User
     */
    @Query(value = "  SELECT * FROM risesin_user WHERE user_account = :account AND phone = :phone AND status = 0", nativeQuery = true)
    User getUser(@Param("account") String account, @Param("phone") String phone);

    /**
     * 获取用户
     *
     * @param phone 手机号
     * @return User
     */
    @Query(value = "SELECT * FROM risesin_user WHERE phone = :phone AND status = 0", nativeQuery = true)
    User getUser(@Param("phone") String phone);

    /**
     * 获取是否有该用户
     *
     * @param userAccount 用户账号
     * @param type        用户类型
     * @return int
     */
    @Query(value = "SELECT count(*) FROM risesin_user WHERE user_account = :account AND user_type=:userType", nativeQuery = true)
    int selectCount(@Param("account") String userAccount, @Param("userType") Integer type);

}
