package com.risesin.system.dao.product;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.product.entity.Product;
import com.risesin.systemapi.product.interfaceresult.ProductNames;
import com.risesin.systemapi.product.vo.QueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * product数据访问接口
 *
 * @author Administrator
 */
public interface ProductDao extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>, BaseDao<Product, Integer> {

//    @Modifying
//    @Query("update Product p set p.status=:status where p.id=:id ")
//    void updateStatus(@Param("id") Integer id, @Param("id") String status);


    /**
     * 产品名称集合查询
     *
     * @param status 产品状态
     * @return list
     */
    @Query(value = "SELECT pk_id as pkId,product_name as productName FROM t_product WHERE status=:status", nativeQuery = true)
    List<ProductNames> getProductNames(@Param("status") Byte status);

//    @Query(value = "SELECT p.pk_id as productId, a.pk_id as applyId FROM `t_product` p left join t_product_apply_condition a " +
//            "on p.fk_application_condition = a.pk_id where p.financing_method_json not in (:ids)", nativeQuery = true)

    @Query("select new com.risesin.systemapi.product.vo.QueryResult(p,a) from Product p left join com.risesin.systemapi.product.entity.ProductApplyCondition a " +
            "on p.fk_application_condition = a.id where p.financingMethodJson not in (:ids)")
    List<QueryResult> findExcludeTypes(@Param(value = "ids") List<String> ids);

}
