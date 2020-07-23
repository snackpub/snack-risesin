package com.risesin.user.service.impl;

import com.risesin.core.jpaplus.extension.api.ApiException;
import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.core.tool.utils.*;
import com.risesin.user.dao.UserDao;
import com.risesin.user.entity.User;
import com.risesin.user.entity.UserExt;
import com.risesin.user.entity.UserInfo;
import com.risesin.user.service.IUserExtService;
import com.risesin.user.service.IUserService;
import com.risesin.user.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * sysUser服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserDao userDao;


    private IUserExtService iUserExtService;

    /**
     * 条件查询+分页
     */
    @Override
    public Page<User> findSearch(Map whereMap, Query query) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return userDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     */
    @Override
    public User findById(String id) {
        return SqlHelper.optional(userDao.findById(id));
    }

    @Override
    public void add(UserVO userVO) {
        String idGenerator = SnowflakeIDGenerator.idGenerator();
        userVO.setId(idGenerator);
        User user = new User();
        if (Func.isNotEmpty(user.getPhone())) {
            user.setPhone(DigestUtil.encrypt(user.getPhone()));
        }
        int cnt = userDao.selectCount(userVO.getAccount(), userVO.getUserType());
        if (cnt > 0) {
            throw new ApiException("当前用户已存在!");
        }
        BeanUtils.copyProperties(userVO, user);
        userDao.save(user);
        if (userVO.getUserExt() == null) {
            UserExt userExt = new UserExt();
            userExt.setId(idGenerator);
            iUserExtService.add(userExt);
        } else {
            userVO.getUserExt().setId(idGenerator);
            iUserExtService.add(userVO.getUserExt());
        }
    }

    @Override
    public void update(UserVO userVO) {
        // 判断主键
        if (StringUtils.isEmpty(userVO.getId())) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        // 保存user
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userDao.update(user, user.getId());
        if (!Objects.isNull(userVO.getUserExt())) {
            userVO.getUserExt().setId(userVO.getId());
            // 保存user拓展类
            iUserExtService.update(userVO.getUserExt());
        }

    }

    @Override
    public void deleteById(String id) {
        userDao.updateStatus(User.class, id, SqlConstant.LOGOUT_OR_DELETE);
    }

    @Override
    public void updateStatus(String id, Byte status) {
        userDao.updateStatus(User.class, id, status);
    }

    @Override
    public boolean removeByIds(List<String> ids) {
        return userDao.removeBatch(User.class, ids, SqlConstant.UPDATE);
    }

    /**
     * 获取角色名
     *
     * @param roleIds 角色ID结集合
     * @return list
     */
    @Override
    public List<String> getRoleName(String roleIds) {
        List<String> roleNames = new ArrayList<>(16);
        if (StringUtil.isNotBlank(roleIds)) {
            roleNames = userDao.getRoleName(Func.toStrArray(roleIds), SqlConstant.START_USEING);
        }
        return roleNames;
    }

    /**
     * 获取部门名
     *
     * @param deptIds 部门ID集合
     * @return list
     */
    @Override
    public List<String> getDeptName(String deptIds) {
        List<String> deptNames = new ArrayList<>(16);
        if (StringUtil.isNotBlank(deptIds)) {
            deptNames = userDao.getDeptName(Func.toStrArray(deptIds), SqlConstant.START_USEING);
        }
        return deptNames;
    }

    /**
     * 获取用户名
     *
     * @param id 用户ID
     * @return str
     */
    @Override
    public String getUserName(String id) {
        return userDao.getUserName(id);
    }

    @Override
    public UserInfo userInfoById(String userId) {
        UserInfo userInfo = new UserInfo();
        User user = SqlHelper.optional(userDao.findById(userId));
        userInfo.setUser(user);
        if (Func.isNotEmpty(user)) {
            List<String> roleAlias = userDao.getRoleAlias(Func.toStrArray(user.getRoleId()));
            userInfo.setRoles(roleAlias);
        }
        return userInfo;
    }

    @Override
    public UserInfo userInfo(String phone) {
        UserInfo userInfo = new UserInfo();
        User user = userDao.getUser(phone);
        userInfo.setUser(user);
        if (Func.isNotEmpty(user)) {
            List<String> roleAlias = userDao.getRoleAlias(Func.toStrArray(user.getRoleId()));
            userInfo.setRoles(roleAlias);
        }
        return userInfo;
    }

    /**
     * 用户信息
     *
     * @param account
     * @param phone
     * @return
     */
    @Override
    public UserInfo userInfo(String account, String phone) {
        UserInfo userInfo = new UserInfo();
        User user = userDao.getUser(account, phone);
        userInfo.setUser(user);
        if (Func.isNotEmpty(user)) {
            List<String> roleAlias = userDao.getRoleAlias(Func.toStrArray(user.getRoleId()));
            userInfo.setRoles(roleAlias);
        }
        return userInfo;
    }

    /**
     * 给用户设置角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID
     * @return bool
     */
    @Override
    public boolean grant(String userId, String roleIds) {
        return SqlHelper.retBool(userDao.grantUserByRole(userId, roleIds));
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 用户名
                if (searchMap.get("userName") != null && !"".equals(searchMap.get("userName"))) {
                    predicateList.add(cb.like(root.get("userName").as(String.class), "%" + (String) searchMap.get("userName") + "%"));
                }
                // 手机号
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }
                // 用户来源
                if (searchMap.get("source") != null && !"".equals(searchMap.get("source"))) {
                    predicateList.add(cb.like(root.get("source").as(String.class), "%" + (String) searchMap.get("source") + "%"));
                }
                // 状态
                if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(root.get("status").as(String.class), "%" + (String) searchMap.get("status") + "%"));
                }
                // 用户类型
                if (searchMap.get("userType") != null && !"".equals(searchMap.get("userType"))) {
                    predicateList.add(cb.like(root.get("userType").as(String.class), Func.toStr(searchMap.get("userType"))));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
