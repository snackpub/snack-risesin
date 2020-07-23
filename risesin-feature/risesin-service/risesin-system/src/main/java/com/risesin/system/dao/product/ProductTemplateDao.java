package com.risesin.system.dao.product;

import com.risesin.systemapi.product.entity.ProductTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * productTemplate数据访问接口
 *
 * @author Administrator
 */
public interface ProductTemplateDao extends JpaRepository<ProductTemplate, Integer>, JpaSpecificationExecutor<ProductTemplate> {

}
