package com.risesin.system.dao.product;

import com.risesin.systemapi.product.entity.ProductDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * productDict数据访问接口
 *
 * @author Administrator
 */
public interface ProductDictDao extends JpaRepository<ProductDict, Integer>, JpaSpecificationExecutor<ProductDict> {

}
