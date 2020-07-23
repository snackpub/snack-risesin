package com.risesin.agent.dao;

import com.risesin.agent.entity.ComPanyReception;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comPanyReception数据访问接口
 *
 * @author Administrator
 */
public interface ComPanyReceptionDao extends JpaRepository<ComPanyReception, String>, JpaSpecificationExecutor<ComPanyReception>, BaseDao<ComPanyReception, String> {

    ComPanyReception findByFkOrderId(String id);
}
