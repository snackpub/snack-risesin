package com.risesin.agent.dao;

import com.risesin.agent.entity.ComUser;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comUser数据访问接口
 *
 * @author Administrator
 */
public interface ComUserDao extends JpaRepository<ComUser, Integer>, JpaSpecificationExecutor<ComUser>, BaseDao<ComUser, Integer> {

//    @Modifying
//    @Query("update ComUser a set " +
//            "a.deptId = CASE WHEN :#{#comUser.deptId} IS NULL THEN a.deptId ELSE :#{#comUser.deptId} END ," +
//            "a.delFlag = CASE WHEN :#{#comUser.delFlag} IS NULL THEN a.delFlag ELSE :#{#comUser.delFlag} END ," +
//            "a.phone = CASE WHEN :#{#comUser.phone} IS NULL THEN a.phone ELSE :#{#comUser.phone} END ," +
//            "a.status =  CASE WHEN :#{#comUser.status} IS NULL THEN a.status ELSE :#{#comUser.status} END ," +
//            "a.updateTime =  CASE WHEN :#{#comUser.updateTime} IS NULL THEN a.updateTime ELSE :#{#comUser.updateTime} END ," +
//            "a.userName =  CASE WHEN :#{#comUser.userName} IS NULL THEN a.userName ELSE :#{#comUser.userName} END ," +
//            "a.source =  CASE WHEN :#{#comUser.source} IS NULL THEN a.source ELSE :#{#comUser.source} END " +
//            "where a.id = :#{#comUser.id}")
//    void update(@Param("comUser")ComUser comUser);

}
