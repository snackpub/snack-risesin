package com.risesin.agent.service;

import com.risesin.agent.dao.ComPanyOrderDao;
import com.risesin.agent.entity.ComPanyOrder;
import com.risesin.agent.entity.ComPanyReception;
import com.risesin.agent.entity.feign.IClientMsgFeign;
import com.risesin.agent.entity.feign.IPlanServiceFeign;
import com.risesin.agent.entity.feign.vo.ActionChildPlan;
import com.risesin.agent.entity.feign.vo.ActionChildPlanVO;
import com.risesin.agent.entity.feign.vo.MsgCenter;
import com.risesin.agent.entity.vo.ComPanyOrderVO;
import com.risesin.agent.entity.vo.ComPanyReceptionVO;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.constant.OrderStatus;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * comPanyOrder服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class ComPanyOrderService {
    /**
     * 订单
     */
    private ComPanyOrderDao comPanyOrderDao;
    /**
     * 分布式雪花id
     */
    private SnowflakeIdWorker snowflakeIdWorker;
    /**
     * 订单回执
     */
    private ComPanyReceptionService comPanyReceptionService;
    /**
     * 子方案服务
     */
    private IPlanServiceFeign planServiceFeign;
    /**
     * 客户端消息服务
     */
    private IClientMsgFeign iClientMsgFeign;
     /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComPanyOrder> findAll() {
        return comPanyOrderDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComPanyOrder> findSearch(Map whereMap, int page, int size) {
        Specification<ComPanyOrder> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comPanyOrderDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComPanyOrder> findSearch(Map whereMap) {
        Specification<ComPanyOrder> specification = createSpecification(whereMap);
        return comPanyOrderDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComPanyOrderVO findById(String pkId) {
        ComPanyOrderVO comPanyOrderVO = new ComPanyOrderVO();
        ComPanyOrder comPanyOrder = comPanyOrderDao.findById(ComPanyOrder.class, pkId);
        BeanUtils.copyProperties(comPanyOrder, comPanyOrderVO);
        // 字典码转换为名称
        comPanyOrderVO.setFlowName(converFlowToName(comPanyOrder.getFlow()));
        if (!StringUtils.isEmpty(comPanyOrder.getPayMethod())) {
            comPanyOrderVO.setPayMethodName(converPaymentMethodName(comPanyOrder.getPayMethod()));
        }
        return comPanyOrderVO;
    }

    /**
     * 增加
     *
     * @param childPlanCode
     */
    public void add(String childPlanCode) {
        // 根据方案编号查询方案, 注入值
        R<ActionChildPlanVO> byChildPlanCode = planServiceFeign.findByChildPlanCode(childPlanCode);
        if(byChildPlanCode.isSuccess()){
            ActionChildPlanVO actionChildPlanVO = byChildPlanCode.getData();
            // 注入数据
            ComPanyOrder comPanyOrder = new ComPanyOrder();
            comPanyOrder.setChildPlanCode(childPlanCode);
            comPanyOrder.setChildPlanName(actionChildPlanVO.getChildPlanName());
            comPanyOrder.setPayee(actionChildPlanVO.getPayee());
            comPanyOrder.setPayeeBankName(actionChildPlanVO.getPayeeBankName());
            comPanyOrder.setPayeeBankCode(actionChildPlanVO.getPayeeBankCode());
            comPanyOrder.setAmount(actionChildPlanVO.getFinalAmount());
            // TODO 设置创建人id  和创建人姓名
//        comPanyOrder.setCreateId();
            comPanyOrder.setId(Func.toStr(snowflakeIdWorker.nextId()));
            comPanyOrderDao.save(comPanyOrder);
        }else{
            throw new ServiceException(ResultCode.FAILURE);
        }

    }

    /**
     * 修改
     *
     * @param comPanyOrder
     */
    public void update(ComPanyOrder comPanyOrder) {
        comPanyOrderDao.update(comPanyOrder, comPanyOrder.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(String pkId) {
        comPanyOrderDao.updateStatus(ComPanyOrder.class, pkId, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComPanyOrder> createSpecification(Map searchMap) {

        return new Specification<ComPanyOrder>() {

            @Override
            public Predicate toPredicate(Root<ComPanyOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 方案编号
                if (searchMap.get("childPlanCode") != null && !"".equals(searchMap.get("childPlanCode"))) {
                    predicateList.add(cb.like(root.get("childPlanCode").as(String.class), "%" + (String) searchMap.get("childPlanCode") + "%"));
                }
                // 流水号
                if (searchMap.get("sequenceNumber") != null && !"".equals(searchMap.get("sequenceNumber"))) {
                    predicateList.add(cb.like(root.get("sequenceNumber").as(String.class), "%" + (String) searchMap.get("sequenceNumber") + "%"));
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
                // 凭证图片名称
                if (searchMap.get("receiptImgName") != null && !"".equals(searchMap.get("receiptImgName"))) {
                    predicateList.add(cb.like(root.get("receiptImgName").as(String.class), "%" + (String) searchMap.get("receiptImgName") + "%"));
                }
                // 凭证图片地址
                if (searchMap.get("receiptPath") != null && !"".equals(searchMap.get("receiptPath"))) {
                    predicateList.add(cb.like(root.get("receiptPath").as(String.class), "%" + (String) searchMap.get("receiptPath") + "%"));
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
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // flow 流程
                if (searchMap.get("flow") != null ) {
                    predicateList.add(cb.equal(root.get("flow").as(String.class), searchMap.get("flow")));
                }
                // 企业用户id
                if (searchMap.get("enterpriseUserId") != null && !"".equals(searchMap.get("enterpriseUserId"))) {
                    predicateList.add(cb.equal(root.get("entUserId").as(String.class), searchMap.get("enterpriseUserId")));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public Boolean removeByIds(List<String> ids) {
        return comPanyOrderDao.removeBatch(ComPanyOrder.class, ids, SqlConstant.UPDATE);
    }

    public void updateStatus(String id, Byte status) {
        comPanyOrderDao.updateStatus(ComPanyOrder.class, id, status);
    }

    /**
     * 审核
     *
     * @param comPanyOrder
     */
    public void verifier(ComPanyOrder comPanyOrder) {
        // 注入状态
        comPanyOrder.setFlow(RisesinConstant.APPROVE);
        this.update(comPanyOrder);
        // 判断审核是否通过
        if (OrderStatus.Refused.getStatusCode().equals(comPanyOrder.getFlow())) {
            // 修改执行方案流程 关闭
            ActionChildPlan actionChildPlan = new ActionChildPlan();
            actionChildPlan.setChildPlanCode(comPanyOrder.getChildPlanCode());
            actionChildPlan.setFlow(RisesinConstant.CLOSE);
            planServiceFeign.updateFlowByCode(actionChildPlan);
        }else{
            // 修改执行方案流程 放款
            ActionChildPlan actionChildPlan = new ActionChildPlan();
            actionChildPlan.setChildPlanCode(comPanyOrder.getChildPlanCode());
            actionChildPlan.setFlow(RisesinConstant.LOAN);
            planServiceFeign.updateFlowByCode(actionChildPlan);
        }

    }

    /**
     * 付款回执
     *
     * @param comPanyReception
     */
    public void paymentReceipt(ComPanyReception comPanyReception) {
        comPanyReceptionService.add(comPanyReception);
        // TODO 通知C端和 咨询端 消息表里面添加一条消息
        MsgCenter msgCenter = new MsgCenter();
        msgCenter.setChildPlanCode(comPanyReception.getFkOrderId());
        msgCenter.setMsgTitle("请确认收款");
        msgCenter.setMsgContent("您有一个方案待确认");
        msgCenter.setMsgType((byte)9);
        iClientMsgFeign.add(msgCenter);

    }

    /**
     * 回执详情
     *
     * @param orderCode
     * @return
     */
    public ComPanyReceptionVO receiptDetails(String orderCode) {
        // 查询出bean
        ComPanyOrder comPanyOrder = comPanyOrderDao.findById(ComPanyOrder.class, orderCode);
        ComPanyReception comPanyReception = comPanyReceptionService.findByOrderId(orderCode);

        ComPanyReceptionVO comPanyReceptionVO = new ComPanyReceptionVO();
        BeanUtils.copyProperties(comPanyReception, comPanyReceptionVO);
        comPanyReceptionVO.setChildPlanName(comPanyOrder.getChildPlanName());
        return comPanyReceptionVO;
    }

    /**
     * 字典码转换名称
     *
     * @param code
     * @return
     */
    private String converFlowToName(byte code) {
        switch (code) {
            case RisesinConstant.NOT_LENDING:
                return "未付款";
            case RisesinConstant.APPROVE:
                return "放款中";
            case RisesinConstant.REFUSED:
                return "审核拒绝";
            case RisesinConstant.HAS_LENDING:
                return "已放款";
            default:
                return "";
        }
    }

    private String converPaymentMethodName(String code) {
        switch (code) {
            case RisesinConstant.PAY_CHANNEL_WX_MWEB:
                return "微信支付";
            case RisesinConstant.PAY_CHANNEL_ALIPAY_PC:
                return "支付宝支付";
            case RisesinConstant.PAY_CHANNEL_WANGYING:
                return "网银支付";
            default:
                return "";
        }
    }
}
