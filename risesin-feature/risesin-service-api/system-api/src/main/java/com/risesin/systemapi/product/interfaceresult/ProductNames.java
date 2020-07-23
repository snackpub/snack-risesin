package com.risesin.systemapi.product.interfaceresult;


/**
 * 产品名称集合
 *
 * @author honey
 * @date 2019/12/5 15:53
 **/
public interface ProductNames {

    /**
     * 产品Id
     *
     * @return ${integer}
     */
    Integer getPkId();

    /**
     * 产品名称
     *
     * @return ${string}
     */
    String getProductName();
}
