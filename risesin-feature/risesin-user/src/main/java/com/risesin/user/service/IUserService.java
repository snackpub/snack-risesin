package com.risesin.user.service;


import com.risesin.core.jpaplus.support.Query;
import com.risesin.user.entity.User;
import com.risesin.user.entity.UserInfo;
import com.risesin.user.vo.UserVO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public interface IUserService {

    /**
     * 根据条件分页查询
     *
     * @param whereMap
     * @param query
     * @return
     */
    Page<User> findSearch(Map whereMap, Query query);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    User findById(String id);

    /**
     * 添加用户
     *
     * @param user
     */
    void add(UserVO user);

    /**
     * 更新用户
     *
     * @param user
     */
    void update(UserVO user);

    /**
     * 根据id逻辑删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 获取角色名
     *
     * @param roleIds
     * @return
     */
    List<String> getRoleName(String roleIds);

    /**
     * 获取部门名
     *
     * @param deptIds
     * @return
     */
    List<String> getDeptName(String deptIds);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    boolean removeByIds(List<String> ids);

    /**
     * 授权
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean grant(String userId, String roleIds);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     */
    void updateStatus(String id, Byte status);

    /**
     * 根据id获取用户名
     *
     * @param id
     * @return
     */
    String getUserName(String id);

    /**
     * 用户信息
     *
     * @param userId
     * @return
     */
    UserInfo userInfoById(String userId);


    /**
     * 用户信息
     *
     * @param phone
     * @return
     */
    UserInfo userInfo(String phone);

    /**
     * 用户信息
     *
     * @param account
     * @param phone
     * @return
     */
    UserInfo userInfo(String account, String phone);
}
