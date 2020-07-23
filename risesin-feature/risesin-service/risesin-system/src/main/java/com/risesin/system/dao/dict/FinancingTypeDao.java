package com.risesin.system.dao.dict;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.FinancingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * 融资类型数据访问接口
 *
 * @author Administrator
 */
public interface FinancingTypeDao extends JpaRepository<FinancingType, Integer>, JpaSpecificationExecutor<FinancingType>, BaseDao<FinancingType, Integer> {
    List<FinancingType> findByParentCode(String parentCode);

    FinancingType findByCode(String code);

    List<FinancingType> findAllByStatus(byte status);
}
