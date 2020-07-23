package com.risesin.system.dao.product;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.product.entity.ProductApplyCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * productApplyCondition数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface ProductApplyConditionDao extends JpaRepository<ProductApplyCondition, String>, JpaSpecificationExecutor<ProductApplyCondition>, BaseDao<ProductApplyCondition, String> {

}
