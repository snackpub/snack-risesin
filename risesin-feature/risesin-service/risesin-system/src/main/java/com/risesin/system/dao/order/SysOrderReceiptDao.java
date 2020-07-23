package com.risesin.system.dao.order;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.order.entity.SysOrderReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * sysOrderReceipt数据访问接口
 *
 * @author Administrator
 */
public interface SysOrderReceiptDao extends BaseDao<SysOrderReceipt, String> {

    /**
     * 根据订单编号查询订单回执详情
     *
     * @param orderCode 订单Code
     * @return SysOrderReceipt
     */
    SysOrderReceipt findByOrderCode(String orderCode);

    /**
     * 修改回执订单状态
     *
     * @param orderCode 订单回执编号
     * @param flow      流程状态
     * @return int
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update SysOrderReceipt o set o.flow=:flow where o.orderCode = :orderCode")
    int updateReceiptCodeFlow(@Param("orderCode") String orderCode, @Param(("flow")) Byte flow);
}
