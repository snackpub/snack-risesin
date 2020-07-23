package com.risesin.system.dao.product;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.product.entity.ProductAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * productAgency数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface ProductAgencyDao extends JpaRepository<ProductAgency, Integer>, JpaSpecificationExecutor<ProductAgency>, BaseDao<ProductAgency, Integer> {

    /**
     * 根据机构ID删除
     *
     * @param id 机构ID
     * @return int
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM t_product_agency WHERE fk_product_id=:id", nativeQuery = true)
    int removeByProductId(@Param("id") Integer id);


}
