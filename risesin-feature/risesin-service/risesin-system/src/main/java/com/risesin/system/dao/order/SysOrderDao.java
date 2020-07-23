package com.risesin.system.dao.order;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.order.entity.SysOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 系统收款订单(前置，咨询)数据访问接口
 *
 * @author Administrator
 */
public interface SysOrderDao extends BaseDao<SysOrder, String> {


    /**
     * 查询所有已支付的金额和
     *
     * @param id   企业用户id
     * @param flow 状态
     * @return
     */
    @Query("select sum(o.amount) from SysOrder o where o.entUserId = :id and o.flow = :flow")
    BigDecimal sumMoney(@Param("id") String id, @Param("flow") Byte flow);

    /**
     * @param id   企业用户id
     * @param flow 状态:待支付(2)
     * @return
     */
    Integer countByEntUserIdAndFlow(String id, Byte flow);

    @Query("from SysOrder where id = :orderCode")
    SysOrder getOrderDetails(@Param("orderCode") String orderCode);


    /**
     * 确认收款
     *
     * @param orderCode   订单编号
     * @param flow        缴费状态
     * @param confirmTime 确认时间
     * @return int
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update SysOrder o set o.flow=:flow,o.confirmTime=:confirmTime where o.id = :orderCode")
    int updateOrderCodeFlow(@Param("orderCode") String orderCode, @Param("confirmTime") LocalDateTime confirmTime, @Param(("flow")) Byte flow);

    /**
     * 查询当年某月的消费总额(月总额)(C端调用)
     *
     * @param id            企业用户id
     * @param paidFlow      状态：已支付(4)
     * @param confirmedFlow 状态：确认收款(5)
     * @param year          年
     * @param month         月
     * @return
     */
    @Query(value = "select sum(o.amount) from t_sys_order o where o.fk_ent_user_id = :id and YEAR(o.pay_time)= :year " +
            "and MONTH(o.pay_time) = :month and (o.flow = :paidFlow or o.flow = :confirmedFlow)", nativeQuery = true)
    BigDecimal getMonthAmount(@Param("id") String id, @Param("paidFlow") Byte paidFlow, @Param("confirmedFlow") Byte confirmedFlow, @Param("year") Integer year, @Param("month") Integer month);

    /**
     * 查询咨询费用总额
     *
     * @param id            用户id
     * @param paidFlow      状态：已支付(4)
     * @param confirmedFlow 状态：确认收款(5)
     * @param type          费用类型产品收费类型和咨询收费类型
     * @return
     */
    @Query(value = "select sum(o.amount) amount from t_sys_order o where o.fk_ent_user_id = :id" +
            " and o.expense_type = :type and (o.flow = :paidFlow or o.flow = :confirmedFlow)", nativeQuery = true)
    BigDecimal consultAndOtherFee(@Param("id") String id, @Param("paidFlow") Byte paidFlow, @Param("confirmedFlow") Byte confirmedFlow, @Param("type") Byte type);

    /**
     * 发票总额
     *
     * @param id          用户id
     * @param flow        状态：确认收款(5)
     * @param productType 产品收费
     * @param consultType 咨询服务费
     * @return
     */
    @Query(value = "select sum(o.amount) amount from t_sys_order o where o.fk_ent_user_id = :id and" +
            " o.flow = :flow and o.expense_type = :productType or o.expense_type = :consultType", nativeQuery = true)
    BigDecimal invoiceAmount(@Param("id") String id, @Param("flow") Byte flow, @Param("productType") Byte productType, @Param("consultType") Byte consultType);

    /**
     * 根据子方案code
     *
     * @param childPlanCode 子方案id
     * @param flow          待支付(2)
     * @return
     */
    SysOrder findByChildPlanCodeAndFlow(String childPlanCode, byte flow);


}
