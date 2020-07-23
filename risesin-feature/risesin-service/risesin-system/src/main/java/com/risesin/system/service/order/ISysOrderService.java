package com.risesin.system.service.order;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.order.entity.SysOrder;
import com.risesin.systemapi.order.vo.SysOrderCheckResultVO;
import com.risesin.systemapi.order.vo.SysOrderVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * 系统订单
 *
 * @author honey
 * @date 2019/12/23 18:02
 */
public interface ISysOrderService extends RisesinBaseService<SysOrder, String> {

    /**
     * 自定义分页
     *
     * @param whereMap 参数条件
     * @param query    分页对象
     * @return Page<RisesinClient>
     */
    Page<SysOrder> findSearch(Map whereMap, Query query);

    /**
     * 修改对象
     *
     * @param order 对象
     */
    void update(SysOrder order);

    /**
     * 增加订单
     *
     * @param orderVO vo
     */
    void add(SysOrderVO orderVO);

    /**
     * 订单审核
     *
     * @param resultVO vo
     * @return str
     */
    String verifier(SysOrderCheckResultVO resultVO);

    /**
     * 确认收款
     *
     * @param order 订单对象
     * @return bool
     */
    boolean confirmPayment(SysOrder order);

    /**
     * 获取银行卡账号信息
     *
     * @param childPlanCode 预案id
     * @return SysOrder
     */
    SysOrder getUnionAcount(String childPlanCode);

    /**
     * 待支付订单数
     *
     * @param entUserId 企业用户id
     * @return int
     */
    int getUnpaidOrderNum(String entUserId);
}
