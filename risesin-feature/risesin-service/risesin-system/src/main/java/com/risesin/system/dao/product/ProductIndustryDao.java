package com.risesin.system.dao.product;

import com.risesin.systemapi.product.entity.ProductIndustry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * productIndustry数据访问接口
 *
 * @author Administrator
 */
public interface ProductIndustryDao extends JpaRepository<ProductIndustry, Integer>, JpaSpecificationExecutor<ProductIndustry> {

}
