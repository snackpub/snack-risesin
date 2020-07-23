package com.risesin.agent.dao;

import com.risesin.agent.entity.ComMsgCenter;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comMsgCenter数据访问接口
 *
 * @author Administrator
 */
public interface ComMsgCenterDao extends JpaRepository<ComMsgCenter, Integer>, JpaSpecificationExecutor<ComMsgCenter>, BaseDao<ComMsgCenter, Integer> {

}
