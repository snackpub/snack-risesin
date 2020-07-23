package com.risesin.system.dao.product;

import com.risesin.systemapi.product.entity.ProductArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * productArea数据访问接口
 *
 * @author Administrator
 */
public interface ProductAreaDao extends JpaRepository<ProductArea, Integer>, JpaSpecificationExecutor<ProductArea> {

}
