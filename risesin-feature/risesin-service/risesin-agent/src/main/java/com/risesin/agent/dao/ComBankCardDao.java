package com.risesin.agent.dao;

import com.risesin.agent.entity.ComBankCard;
import com.risesin.core.base.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * comBankCard数据访问接口
 *
 * @author Administrator
 */
public interface ComBankCardDao extends JpaRepository<ComBankCard, Integer>, JpaSpecificationExecutor<ComBankCard>, BaseDao<ComBankCard, Integer> {

}
