package com.risesin.agent.service;

import com.risesin.agent.dao.ComPanyOrderDao;
import com.risesin.agent.dao.ComPanyReceptionDao;
import com.risesin.agent.entity.ComPanyOrder;
import com.risesin.agent.entity.ComPanyReception;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
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
 * comPanyReception服务层
 *
 * @author Administrator
 */
@Service
public class ComPanyReceptionService {

    @Autowired
    private ComPanyReceptionDao comPanyReceptionDao;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private ComPanyOrderDao comPanyOrderDao;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComPanyReception> findAll() {
        return comPanyReceptionDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComPanyReception> findSearch(Map whereMap, int page, int size) {
        Specification<ComPanyReception> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comPanyReceptionDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComPanyReception> findSearch(Map whereMap) {
        Specification<ComPanyReception> specification = createSpecification(whereMap);
        return comPanyReceptionDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComPanyReception findById(String pkId) {
        return comPanyReceptionDao.findById(ComPanyReception.class, pkId);
    }

    /**
     * 根据订单编号查询实体
     *
     * @param id
     * @return
     */
    public ComPanyReception findByOrderId(String id) {
        return comPanyReceptionDao.findByFkOrderId(id);
    }

    /**
     * 增加
     *
     * @param comPanyReception
     */
    public void add(ComPanyReception comPanyReception) {
        // 生成回执详情
        comPanyReception.setId(Func.toStr(snowflakeIdWorker.nextId())); // 雪花分布式ID生成器
        comPanyReceptionDao.save(comPanyReception);
        // 修改订单流程状态
        ComPanyOrder comPanyOrder = new ComPanyOrder();
        comPanyOrder.setSequenceNumber(comPanyReception.getSequenceNumber());
        comPanyOrder.setPayTime(comPanyReception.getPayTime());
        comPanyOrder.setPayMethod(RisesinConstant.PAY_CHANNEL_WANGYING);
        comPanyOrder.setFlow(RisesinConstant.HAS_LENDING);
        comPanyOrderDao.update(comPanyOrder, comPanyReception.getFkOrderId());
    }

    /**
     * 修改
     *
     * @param comPanyReception
     */
    public void update(ComPanyReception comPanyReception) {
        comPanyReceptionDao.save(comPanyReception);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(String pkId) {
        comPanyReceptionDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComPanyReception> createSpecification(Map searchMap) {

        return new Specification<ComPanyReception>() {

            @Override
            public Predicate toPredicate(Root<ComPanyReception> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 回执单id
                if (searchMap.get("pkId") != null && !"".equals(searchMap.get("pkId"))) {
                    predicateList.add(cb.like(root.get("pkId").as(String.class), "%" + (String) searchMap.get("pkId") + "%"));
                }
                // 订单编号
                if (searchMap.get("fkOrderId") != null && !"".equals(searchMap.get("fkOrderId"))) {
                    predicateList.add(cb.like(root.get("fkOrderId").as(String.class), "%" + (String) searchMap.get("fkOrderId") + "%"));
                }
                // 收款方
                if (searchMap.get("payee") != null && !"".equals(searchMap.get("payee"))) {
                    predicateList.add(cb.like(root.get("payee").as(String.class), "%" + (String) searchMap.get("payee") + "%"));
                }
                // 收款方银行开户行
                if (searchMap.get("payeeBankName") != null && !"".equals(searchMap.get("payeeBankName"))) {
                    predicateList.add(cb.like(root.get("payeeBankName").as(String.class), "%" + (String) searchMap.get("payeeBankName") + "%"));
                }
                // 收款方银行卡号
                if (searchMap.get("payeeBankCode") != null && !"".equals(searchMap.get("payeeBankCode"))) {
                    predicateList.add(cb.like(root.get("payeeBankCode").as(String.class), "%" + (String) searchMap.get("payeeBankCode") + "%"));
                }
                // 付款方
                if (searchMap.get("payer") != null && !"".equals(searchMap.get("payer"))) {
                    predicateList.add(cb.like(root.get("payer").as(String.class), "%" + (String) searchMap.get("payer") + "%"));
                }
                // 付款方银行开户行
                if (searchMap.get("payerBankName") != null && !"".equals(searchMap.get("payerBankName"))) {
                    predicateList.add(cb.like(root.get("payerBankName").as(String.class), "%" + (String) searchMap.get("payerBankName") + "%"));
                }
                // 付款方银行卡号
                if (searchMap.get("payerBankCode") != null && !"".equals(searchMap.get("payerBankCode"))) {
                    predicateList.add(cb.like(root.get("payerBankCode").as(String.class), "%" + (String) searchMap.get("payerBankCode") + "%"));
                }
                // 凭证图片名称
                if (searchMap.get("receiptImgName") != null && !"".equals(searchMap.get("receiptImgName"))) {
                    predicateList.add(cb.like(root.get("receiptImgName").as(String.class), "%" + (String) searchMap.get("receiptImgName") + "%"));
                }
                // 凭证图片地址
                if (searchMap.get("receiptPath") != null && !"".equals(searchMap.get("receiptPath"))) {
                    predicateList.add(cb.like(root.get("receiptPath").as(String.class), "%" + (String) searchMap.get("receiptPath") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
