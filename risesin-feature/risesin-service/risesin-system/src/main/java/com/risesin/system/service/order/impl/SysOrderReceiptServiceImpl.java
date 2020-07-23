package com.risesin.system.service.order.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.order.SysOrderDao;
import com.risesin.system.dao.order.SysOrderReceiptDao;
import com.risesin.system.service.order.ISysOrderReceiptService;
import com.risesin.systemapi.order.entity.SysOrder;
import com.risesin.systemapi.order.entity.SysOrderReceipt;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sysOrderReceipt服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class SysOrderReceiptServiceImpl extends RisesinBaseServiceImpl<SysOrderReceiptDao, SysOrderReceipt, String> implements ISysOrderReceiptService {

    /**
     * 已支付
     */
    private static final byte PAID = 4;

    private SysOrderDao sysOrderDao;

    @Override
    public Page<SysOrderReceipt> findSearch(Map whereMap, Query query) {
        Specification<SysOrderReceipt> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }


    @Override
    public SysOrderReceipt findByReceiptCode(String receiptCode) {
        return SqlHelper.optional(baseDao.findById(receiptCode));
    }


    @Override
    public void add(@NotNull SysOrderReceipt sysOrderReceipt) {
        sysOrderReceipt.setReceiptCode(SnowflakeIDGenerator.idGenerator());
        baseDao.save(sysOrderReceipt);
    }

    @Override
    public void update(SysOrderReceipt sysOrderReceipt) {
        baseDao.update(sysOrderReceipt, sysOrderReceipt.getReceiptCode());
    }

    @Override
    public boolean updateReceiptCodeFlow(String orderCode, Byte flow) {
        int row = baseDao.updateReceiptCodeFlow(orderCode, flow);
        return SqlHelper.retBool(row);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap 参数
     * @return Specification<SysOrderReceipt>
     */
    private Specification<SysOrderReceipt> createSpecification(Map searchMap) {

        return new Specification<SysOrderReceipt>() {

            @Override
            public Predicate toPredicate(Root<SysOrderReceipt> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 方案编号
                if (searchMap.get("childPlanCode") != null && !"".equals(searchMap.get("childPlanCode"))) {
                    predicateList.add(cb.like(root.get("childPlanCode").as(String.class), "%" + (String) searchMap.get("childPlanCode") + "%"));
                }
                // 订单编号
                if (searchMap.get("orderCode") != null && !"".equals(searchMap.get("orderCode"))) {
                    predicateList.add(cb.like(root.get("orderCode").as(String.class), "%" + (String) searchMap.get("orderCode") + "%"));
                }
                // 收款方
                if (searchMap.get("payee") != null && !"".equals(searchMap.get("payee"))) {
                    predicateList.add(cb.like(root.get("payee").as(String.class), "%" + (String) searchMap.get("payee") + "%"));
                }
                // 收款方银行开户行/支付宝/微信。例如：农业银行
                if (searchMap.get("payeeBankName") != null && !"".equals(searchMap.get("payeeBankName"))) {
                    predicateList.add(cb.like(root.get("payeeBankName").as(String.class), "%" + (String) searchMap.get("payeeBankName") + "%"));
                }
                // 收款方银行卡号/支付宝账号/微信账号
                if (searchMap.get("payeeBankCardCode") != null && !"".equals(searchMap.get("payeeBankCardCode"))) {
                    predicateList.add(cb.like(root.get("payeeBankCardCode").as(String.class), "%" + (String) searchMap.get("payeeBankCardCode") + "%"));
                }
                // 付款方
                if (searchMap.get("payer") != null && !"".equals(searchMap.get("payer"))) {
                    predicateList.add(cb.like(root.get("payer").as(String.class), "%" + (String) searchMap.get("payer") + "%"));
                }
                // 付款方银行开户行/支付宝/微信。例如：农业银行
                if (searchMap.get("payerBankName") != null && !"".equals(searchMap.get("payerBankName"))) {
                    predicateList.add(cb.like(root.get("payerBankName").as(String.class), "%" + (String) searchMap.get("payerBankName") + "%"));
                }
                // 付款方银行卡号/支付宝账号/微信账号
                if (searchMap.get("payerBankCardCode") != null && !"".equals(searchMap.get("payerBankCardCode"))) {
                    predicateList.add(cb.like(root.get("payerBankCardCode").as(String.class), "%" + (String) searchMap.get("payerBankCardCode") + "%"));
                }
                // 流水号
                if (searchMap.get("sequenceNumber") != null && !"".equals(searchMap.get("sequenceNumber"))) {
                    predicateList.add(cb.like(root.get("sequenceNumber").as(String.class), "%" + (String) searchMap.get("sequenceNumber") + "%"));
                }
                // 经手人姓名 (系统自动创建凭证经手人为admin)
                if (searchMap.get("sysUserName") != null && !"".equals(searchMap.get("sysUserName"))) {
                    predicateList.add(cb.like(root.get("sysUserName").as(String.class), "%" + (String) searchMap.get("sysUserName") + "%"));
                }
                // 凭证图片地址 (如为支付宝微信支付，该字段可为空)
                if (searchMap.get("receiptPath") != null && !"".equals(searchMap.get("receiptPath"))) {
                    predicateList.add(cb.like(root.get("receiptPath").as(String.class), "%" + (String) searchMap.get("receiptPath") + "%"));
                }
                // 凭证图片名称 (如为支付宝微信支付，该字段可为空)
                if (searchMap.get("receiptImgName") != null && !"".equals(searchMap.get("receiptImgName"))) {
                    predicateList.add(cb.like(root.get("receiptImgName").as(String.class), "%" + (String) searchMap.get("receiptImgName") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public void paymentReceipt(SysOrderReceipt sysOrderReceipt) {
        sysOrderReceipt.setPayMethod(RisesinConstant.PAY_CHANNEL_WANGYING);
        //保存付款回执
        baseDao.save(sysOrderReceipt);

        // 修改订单流程状态
        SysOrder sysOrder = new SysOrder();
        //付款时间
        sysOrder.setPayTime(sysOrderReceipt.getPayTime());
        //缴费方式
        sysOrder.setChannelId(RisesinConstant.PAY_CHANNEL_WANGYING);
        //订单状态
        sysOrder.setFlow(PAID);
        //付款方
        sysOrder.setPayer(sysOrderReceipt.getPayer());
        //付款方银行卡号
        sysOrder.setPayerBankCardCode(sysOrderReceipt.getPayerBankCode());
        //付款方开户行
        sysOrder.setPayerBankName(sysOrderReceipt.getPayerBankName());
        sysOrderDao.update(sysOrder, sysOrderReceipt.getOrderCode());

    }

    /**
     * 根据订单id查询订单回执详情
     *
     * @param orderCode 订单编号
     * @return SysOrderReceipt
     */
    public SysOrderReceipt findByOrderCode(String orderCode) {
        return baseDao.findByOrderCode(orderCode);
    }
}
