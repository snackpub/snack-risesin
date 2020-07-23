package com.risesin.system.service.order;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.order.entity.SysOrderReceipt;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 订单回执服务接口
 *
 * @author honey
 * @date 2019/12/23
 */
public interface ISysOrderReceiptService extends RisesinBaseService<SysOrderReceipt, String> {

    /**
     * 分页
     *
     * @param whereMap 参数
     * @param query    分页参数
     * @return page
     */
    Page<SysOrderReceipt> findSearch(Map whereMap, Query query);

    /**
     * 根据ID查询实体
     *
     * @param receiptCode 订单回执编号
     * @return SysOrderReceipt
     */
    SysOrderReceipt findByReceiptCode(String receiptCode);

    /**
     * 修改
     *
     * @param sysOrderReceipt 订单回执 对象
     */
    void update(SysOrderReceipt sysOrderReceipt);

    /**
     * 确认收款
     *
     * @param orderCode 订单编号
     * @param flow      流程状态
     * @return bool
     */
    boolean updateReceiptCodeFlow(String orderCode, Byte flow);
}
