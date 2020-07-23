package com.risesin.agent.dao;

import com.risesin.agent.entity.ComfirmAmount;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comfirmAmount数据访问接口
 *
 * @author Administrator
 */
public interface ComfirmAmountDao extends JpaRepository<ComfirmAmount, Integer>, JpaSpecificationExecutor<ComfirmAmount>, BaseDao<ComfirmAmount, Integer> {

}
