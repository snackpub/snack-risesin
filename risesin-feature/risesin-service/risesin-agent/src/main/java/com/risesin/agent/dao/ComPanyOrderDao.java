package com.risesin.agent.dao;

import com.risesin.agent.entity.ComPanyOrder;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comPanyOrder数据访问接口
 *
 * @author Administrator
 */
public interface ComPanyOrderDao extends JpaRepository<ComPanyOrder, String>, JpaSpecificationExecutor<ComPanyOrder>, BaseDao<ComPanyOrder, String> {

}
