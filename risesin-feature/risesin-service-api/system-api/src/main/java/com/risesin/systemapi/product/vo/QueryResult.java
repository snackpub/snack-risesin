package com.risesin.systemapi.product.vo;

import com.risesin.systemapi.product.entity.Product;
import com.risesin.systemapi.product.entity.ProductApplyCondition;
import lombok.Data;

import java.io.Serializable;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-22
 * @DESCRIPTION TODO
 * @since 1.0.0
 */
@Data
public class QueryResult implements Serializable {

    private Product product;

    private ProductApplyCondition productApplyCondition;

    public QueryResult(Product product, ProductApplyCondition productApplyCondition) {
        this.product = product;
        this.productApplyCondition = productApplyCondition;
    }

}
