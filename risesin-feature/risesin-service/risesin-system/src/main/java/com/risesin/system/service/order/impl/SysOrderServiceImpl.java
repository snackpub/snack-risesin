package com.risesin.system.service.order.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.order.SysOrderCheckResultDao;
import com.risesin.system.dao.order.SysOrderDao;
import com.risesin.system.service.order.ISysOrderService;
import com.risesin.systemapi.order.entity.SysOrder;
import com.risesin.systemapi.order.entity.SysOrderCheckResult;
import com.risesin.systemapi.order.vo.FeeAmountVO;
import com.risesin.systemapi.order.vo.FeeCenterCountVO;
import com.risesin.systemapi.order.vo.SysOrderCheckResultVO;
import com.risesin.systemapi.order.vo.SysOrderVO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * sysOrder服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class SysOrderServiceImpl extends RisesinBaseServiceImpl<SysOrderDao, SysOrder, String> implements ISysOrderService {

    /**
     * 审核拒绝
     */
    private static final byte AUDIT_PASSED = -1;
    /**
     * 审核通过
     */
    private static final byte AUDIT_REFUSED = 0;

    /**
     * 审核中
     */
    private static final byte VIRIFYING = 1;
    /**
     * 待支付
     */
    private static final byte UNPAID = 2;
    /**
     * 支付中
     */
    private static final byte PAYING = 3;
    /**
     * 已支付
     */
    private static final byte PAID = 4;
    /**
     * 确认收款
     */
    private static final byte CONFIRMED = 5;
    /**
     * 已超时
     */
    private static final byte TIMEOUT = 6;

    /**
     * 产品收费项
     */
    private static final byte PROD_ITEM = 1;
    /**
     * 咨询服务费项
     */
    private static final byte CONSULTING_SERVICE_FEE = 2;


    private SysOrderDao sysOrderDao;
    private SysOrderCheckResultDao orderCheckResultDao;
    private SysOrderReceiptServiceImpl orderReceiptService;

    @Override
    public Page<SysOrder> findSearch(Map whereMap, Query query) {
        Specification<SysOrder> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return sysOrderDao.findAll(specification, pageRequest);
    }

    @Override
    public SysOrder findById(String orderCode) {
        return sysOrderDao.getOrderDetails(orderCode);
    }

    @Override
    public String verifier(SysOrderCheckResultVO resultVO) {
        String flag;
        if (resultVO.getIsPass() == AUDIT_PASSED) {
            orderCheckResultDao.save(resultVO);
            flag = "审核失败";
        } else {
            flag = "审核通过";
            SysOrder order = new SysOrder();
            order.setId(resultVO.getSysOrderId());
            order.setPayeeBankCardCode(resultVO.getPayeeBankCardCode());
            order.setPayee(resultVO.getPayee());
            order.setPayeeBankName(resultVO.getPayeeBankName());
            // 设置收款方账号
            this.update(order);
            SysOrderCheckResult orderCheckResult = BeanUtil.copy(resultVO, SysOrderCheckResult.class);
            orderCheckResult.setId(SnowflakeIDGenerator.idGenerator());
            orderCheckResultDao.save(orderCheckResult);
        }
        return flag;
    }


    @Override
    public void add(@NotNull SysOrder order) {
        sysOrderDao.save(order);
    }

    @Override
    public void add(@NotNull SysOrderVO orderVO) {
        // TODO 统一从权限框架提供
        //orderVO.setCreateUser(0); /*订单创建者*/
        //orderVO.setFinanceStaff(0); /*财务人员*/
        if (orderVO.getExpenseType() == CONSULTING_SERVICE_FEE) {
            SysOrder sysOrder = new SysOrder();
            sysOrder.setId(SnowflakeIDGenerator.idGenerator());
            BeanUtils.copyProperties(orderVO, sysOrder);
            /* 咨询服务费订单 */
            sysOrder.setOtherCharges(JsonUtil.toJson(orderVO.getFeeItems()));
            sysOrderDao.save(sysOrder);
        }
        /* 有前置收款订单才创建 */
        if (orderVO.getExpenseType() == PROD_ITEM) {
            List<Map<String, Object>> feeItems = orderVO.getFeeItems();
            ArrayList<SysOrder> list2 = new ArrayList<>();
            feeItems.forEach(feeItem -> feeItem.forEach((k, v) -> {
                // exception: detached entity passed to persist
                ArrayList list1 = (ArrayList) v;
                list1.forEach(data -> {
                    SysOrder parse = JsonUtil.parse(JsonUtil.toJson(data), SysOrder.class);
                    parse.setId(SnowflakeIDGenerator.idGenerator());
                    list2.add(parse);
                });
            }));
            // 批量保存子方案收费项订单
            sysOrderDao.saveBatch(list2);
        }
    }


    @Override
    public void update(SysOrder order) {
        baseDao.update(order, order.getId());
    }

    @Override
    public boolean confirmPayment(@NotNull SysOrder order) {
        order.setConfirmTime(LocalDateTime.now());
        boolean b1 = SqlHelper.retBool(sysOrderDao.updateOrderCodeFlow(order.getId(), order.getConfirmTime(), order.getFlow()));
        boolean b2 = orderReceiptService.updateReceiptCodeFlow(order.getId(), order.getFlow());
        return b1 && b2;
    }

    /**
     * 动态条件构建
     */
    @NotNull
    @Contract(value = "_ -> new", pure = true)
    private Specification<SysOrder> createSpecification(Map searchMap) {

        return new Specification<SysOrder>() {

            @Override
            public Predicate toPredicate(Root<SysOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 企业用户id
                if (searchMap.get("enterpriseUserId") != null && !"".equals(searchMap.get("enterpriseUserId"))) {
                    predicateList.add(cb.equal(root.get("entUserId").as(String.class), searchMap.get("enterpriseUserId")));
                }

                // 企业用户id
                if (searchMap.get("sysUserId") != null && !"".equals(searchMap.get("sysUserId"))) {
                    predicateList.add(cb.equal(root.get("sysUserId").as(String.class), searchMap.get("sysUserId")));
                }

                // 缴费状态：审核中(1);待支付(2);支付中(3);已付款(4);确认收款(5);已超时(6)(一个月未支付状态设为"已超时")
                if (searchMap.get("flow") != null) {
                    predicateList.add(cb.equal(root.get("flow").as(String.class), searchMap.get("flow")));
                }

                // 主键(订单编号)
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 所属公司
                if (searchMap.get("enterpriseName") != null && !"".equals(searchMap.get("enterpriseName"))) {
                    predicateList.add(cb.like(root.get("enterpriseName").as(String.class), "%" + (String) searchMap.get("enterpriseName") + "%"));
                }
                // 所属方案（对应融资方案名称）
                if (searchMap.get("planName") != null && !"".equals(searchMap.get("planName"))) {
                    predicateList.add(cb.like(root.get("planName").as(String.class), "%" + (String) searchMap.get("planName") + "%"));
                }
                // 方案code
                if (searchMap.get("childPlanCode") != null && !"".equals(searchMap.get("childPlanCode"))) {
                    predicateList.add(cb.like(root.get("childPlanCode").as(String.class), "%" + (String) searchMap.get("childPlanCode") + "%"));
                }
                // 方案名称（对应子方案名称）
                if (searchMap.get("childPlanName") != null && !"".equals(searchMap.get("childPlanName"))) {
                    predicateList.add(cb.like(root.get("childPlanName").as(String.class), "%" + (String) searchMap.get("childPlanName") + "%"));
                }
                // 缴费类型
                if (searchMap.get("expenseType") != null && !"".equals(searchMap.get("expenseType"))) {
                    predicateList.add(cb.like(root.get("expenseType").as(String.class), "%" + (String) searchMap.get("expenseType") + "%"));
                }
                // 缴费方式 支付宝;微信;网银转账
                if (searchMap.get("channelId") != null && !"".equals(searchMap.get("channelId"))) {
                    predicateList.add(cb.equal(root.get("channelId").as(String.class), (String) searchMap.get("channelId")));
                }
                // 支付渠道用户ID(微信openID或支付宝账号等第三方支付账号
                if (searchMap.get("channelUserId") != null && !"".equals(searchMap.get("channelUserId"))) {
                    predicateList.add(cb.like(root.get("channelUserId").as(String.class), "%" + (String) searchMap.get("channelUserId") + "%"));
                }
                // 额外参数
                if (searchMap.get("extra") != null && !"".equals(searchMap.get("extra"))) {
                    predicateList.add(cb.like(root.get("extra").as(String.class), "%" + (String) searchMap.get("extra") + "%"));
                }
                // 描述信息
                if (searchMap.get("body") != null && !"".equals(searchMap.get("body"))) {
                    predicateList.add(cb.like(root.get("body").as(String.class), "%" + (String) searchMap.get("body") + "%"));
                }
                // 异步通知地址
                if (searchMap.get("notifyUrl") != null && !"".equals(searchMap.get("notifyUrl"))) {
                    predicateList.add(cb.like(root.get("notifyUrl").as(String.class), "%" + (String) searchMap.get("notifyUrl") + "%"));
                }
                // 收款方银行卡号
                if (searchMap.get("payeeBankCardCode") != null && !"".equals(searchMap.get("payeeBankCardCode"))) {
                    predicateList.add(cb.like(root.get("payeeBankCardCode").as(String.class), "%" + (String) searchMap.get("payeeBankCardCode") + "%"));
                }
                // 收款方
                if (searchMap.get("payee") != null && !"".equals(searchMap.get("payee"))) {
                    predicateList.add(cb.like(root.get("payee").as(String.class), "%" + (String) searchMap.get("payee") + "%"));
                }
                // 收款方银行开户行
                if (searchMap.get("payeeBankName") != null && !"".equals(searchMap.get("payeeBankName"))) {
                    predicateList.add(cb.like(root.get("payeeBankName").as(String.class), "%" + (String) searchMap.get("payeeBankName") + "%"));
                }
                // 付款方
                if (searchMap.get("payer") != null && !"".equals(searchMap.get("payer"))) {
                    predicateList.add(cb.like(root.get("payer").as(String.class), "%" + (String) searchMap.get("payer") + "%"));
                }
                // 付款方银行开户行
                if (searchMap.get("payerBankName") != null && !"".equals(searchMap.get("payerBankName"))) {
                    predicateList.add(cb.like(root.get("payerBankName").as(String.class), "%" + (String) searchMap.get("payerBankName") + "%"));
                }
                // 创建时间区间查询
                if (searchMap.get("startDate") != null && !"".equals(searchMap.get("startDate"))) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), (String) searchMap.get("startDate")));
                }
                if (searchMap.get("endDate") != null && !"".equals(searchMap.get("endDate"))) {
                    predicateList.add(cb.lessThanOrEqualTo((root.get("createTime").as(String.class)), (String) searchMap.get("endDate")));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


    public FeeCenterCountVO getFeeCount(String id) {
        FeeCenterCountVO feeCenterCountVO = new FeeCenterCountVO();

        //查询消费总额
        BigDecimal paidMoney = sysOrderDao.sumMoney(id, CONFIRMED);
        feeCenterCountVO.setPaidMoney(paidMoney == null ? new BigDecimal("0") : paidMoney);
        //发票金额，目前与消费金额一致
        feeCenterCountVO.setInvoiceMoney(paidMoney == null ? new BigDecimal("0") : paidMoney);

        //查询待支付总额
        BigDecimal unpaidMoney = sysOrderDao.sumMoney(id, UNPAID);
        feeCenterCountVO.setUnpaidMoney(unpaidMoney == null ? new BigDecimal("0") : unpaidMoney);
        //待支付方案数
        Integer unpaidNum = sysOrderDao.countByEntUserIdAndFlow(id, UNPAID);

        feeCenterCountVO.setUnpaidNum(unpaidNum == null ? 0 : unpaidNum);
        return feeCenterCountVO;
    }

    /**
     * 查询当年某月的消费总额(月总额)(C端调用)
     *
     * @param params
     * @return
     */
    public BigDecimal getMonthAmount(Map<String, Object> params) {
        BigDecimal monthAmount = sysOrderDao.getMonthAmount((String) params.get("enterpriseUserId"), PAID, CONFIRMED, (Integer) params.get("year"), (Integer) params.get("month"));
        if (null != monthAmount) {
            return monthAmount;
        }
        return new BigDecimal("0");
    }

    /**
     * 咨询费用总额和收费项总额
     *
     * @param enterpriseUserId
     * @return
     */
    public FeeAmountVO getFeeAmount(String enterpriseUserId) {
        FeeAmountVO feeAmountVO = new FeeAmountVO();
        BigDecimal consultFee = sysOrderDao.consultAndOtherFee(enterpriseUserId, PAID, CONFIRMED, CONSULTING_SERVICE_FEE);
        BigDecimal otherFee = sysOrderDao.consultAndOtherFee(enterpriseUserId, PAID, CONFIRMED, PROD_ITEM);
        BigDecimal invoiceMoney = sysOrderDao.invoiceAmount(enterpriseUserId, CONFIRMED, PROD_ITEM, CONSULTING_SERVICE_FEE);
        feeAmountVO.setConsultFee(consultFee == null ? new BigDecimal("0") : consultFee);
        feeAmountVO.setOtherFee(otherFee == null ? new BigDecimal("0") : otherFee);
        feeAmountVO.setInvoiceMoney(invoiceMoney == null ? new BigDecimal("0") : invoiceMoney);

        return feeAmountVO;
    }

    /**
     * 获取银行卡账号信息
     *
     * @param childPlanCode
     * @return
     */
    @Override
    public SysOrder getUnionAcount(String childPlanCode) {
        return sysOrderDao.findByChildPlanCodeAndFlow(childPlanCode, UNPAID);
    }

    /**
     * 待支付订单数
     *
     * @param entUserId
     * @return
     */
    @Override
    public int getUnpaidOrderNum(String entUserId) {
        return sysOrderDao.countByEntUserIdAndFlow(entUserId, UNPAID);
    }
}
