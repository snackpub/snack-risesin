package com.risesin.enterprise.dao;

import com.risesin.core.base.BaseDao;
import com.risesin.user.entity.EnterpriseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * enterpriseUser数据访问接口
 *
 * @author Administrator
 */
public interface EnterpriseUserDao extends JpaRepository<EnterpriseUser, Integer>, JpaSpecificationExecutor<EnterpriseUser>, BaseDao<EnterpriseUser, Integer> {



    // ...where phone = ? and delFlag = ?
    //EnterpriseUser findEnterpriseUserByPhoneAndDelFlag(String phone, boolean delFlag);

    EnterpriseUser findByPhoneAndStatus(String phone, byte status);


}
