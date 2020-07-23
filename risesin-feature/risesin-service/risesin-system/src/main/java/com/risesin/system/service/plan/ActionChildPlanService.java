package com.risesin.system.service.plan;

import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.calculateInterest.CalculateInterestUtil;
import com.risesin.core.tool.constant.RisesinConstant;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.dao.ext.ExtFileDataDao;
import com.risesin.system.dao.plan.ActionChildPlanDao;
import com.risesin.system.dao.plan.RiskCheckResultDao;
import com.risesin.system.service.order.impl.SysOrderServiceImpl;
import com.risesin.system.service.plan.impl.ActionPlanServiceImpl;
import com.risesin.system.service.product.ProductService;
import com.risesin.systemapi.dict.entity.DataTemplate;
import com.risesin.systemapi.ext.entity.ExtFileData;
import com.risesin.systemapi.order.vo.SysOrderVO;
import com.risesin.systemapi.plan.entity.ActionChildPlan;
import com.risesin.systemapi.plan.entity.ActionPlan;
import com.risesin.systemapi.plan.entity.RiskCheckResult;
import com.risesin.systemapi.plan.interfaceresult.ActionChildPlanResult;
import com.risesin.systemapi.plan.vo.ActionChildPlanVO;
import com.risesin.systemapi.product.vo.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * actionChildPlan服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class ActionChildPlanService {
    /**
     * 子方案DAO
     */
    private ActionChildPlanDao actionChildPlanDao;
    /**
     * 父方案
     */
    private ActionPlanServiceImpl actionPlanService;
    /**
     * 订单服务
     */
    private SysOrderServiceImpl sysOrderServiceImpl;
    /**
     * 产品服务
     */
    private ProductService productService;
    /**
     * 资料拓展DAO
     */
    private ExtFileDataDao extFileDataDao;
    /**
     * 风控审核表
     */
    private RiskCheckResultDao riskCheckResultDao;

    /**
     * 未完成
     **/
    private static final byte[] INCOMPLETEFLOW = {10, 11};


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<ActionChildPlan> findSearch(Map whereMap, int pageNo, int pageSize) {
        Specification<ActionChildPlan> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
        return actionChildPlanDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ActionChildPlan> findSearch(Map whereMap) {
        Specification<ActionChildPlan> specification = createSpecification(whereMap);
        return actionChildPlanDao.findAll(specification);
    }

    /**
     * 子方案增加
     *
     * @param actionChildPlanVO
     */
    @Transactional(rollbackOn = Exception.class)
    public void add(ActionChildPlanVO actionChildPlanVO) {
        // 生成子方案编号
        String code = SnowflakeIDGenerator.idGenerator();
        actionChildPlanVO.setChildPlanCode(code);
        // 计算利息总额
        Map<String, Number> calculateInterestMap = CalculateInterestUtil.calculateInterestAndCaptal(
                actionChildPlanVO.getFinancingAmount(),
                actionChildPlanVO.getInterestRate(),
                actionChildPlanVO.getLoanCycle(),
                actionChildPlanVO.getRepayment()
        );
        // 设置利息与本金
        actionChildPlanVO.setCalculateInterestMap(calculateInterestMap);
        // 转换
        ActionChildPlan actionChildPlan = transferToBean(actionChildPlanVO);
        // 设置利息总额
        actionChildPlan.setInterestTotal((BigDecimal) calculateInterestMap.get(RisesinConstant.TOTAL_INTEREST));
        // 设置流程为完善资料
        actionChildPlan.setFlow(RisesinConstant.COMPLETE_DATA);
        // 根据父方案获取企业id
        ActionPlan plan = actionPlanService.findById(actionChildPlanVO.getPlanId());
        actionChildPlan.setEntUserId(plan.getEntUserId());
        // 保存
        actionChildPlanDao.save(actionChildPlan);

        // 插入资料拓展表
        if (!StringUtils.isEmpty(actionChildPlanVO.getFkProductId()) || Objects.isNull(productService.findById(actionChildPlanVO.getFkProductId()))) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        // 从产品中查询出资料模板
        ProductVO productVO = productService.findById(actionChildPlanVO.getFkProductId());
        List<DataTemplate> dataTemplateList = productVO.getDataTemplateList();
        if (dataTemplateList.size() <= 0) {
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR);
        }
        // 保存资料拓展表
        dataTemplateList.forEach(dataTemplate -> {
            ExtFileData extFileData = new ExtFileData();
            extFileData.setChildPlanCode(code);
            extFileData.setFkDataTemplateId(dataTemplate.getId());
            extFileData.setDataTemplateName(dataTemplate.getField());
            extFileDataDao.save(extFileData);
        });
    }


    /**
     * 修改
     *
     * @param actionChildPlanVO
     */
    public void update(ActionChildPlanVO actionChildPlanVO) {
        ActionChildPlan actionChildPlan = transferToBean(actionChildPlanVO);
        actionChildPlanDao.update(actionChildPlan, actionChildPlan.getChildPlanCode());
    }

    /**
     * 删除
     *
     * @param code
     */
    public void deleteById(String code) {
        this.updateStatus(code, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ActionChildPlan> createSpecification(Map searchMap) {

        return new Specification<ActionChildPlan>() {

            @Override
            public Predicate toPredicate(Root<ActionChildPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                //子方案编号
                if (searchMap.get("childPlanCode") != null && !"".equals(searchMap.get("childPlanCode"))) {
                    predicateList.add(cb.equal(root.get("childPlanCode").as(String.class), searchMap.get("childPlanCode")));
                }
                // 父方案名称
                if (searchMap.get("planId") != null && !"".equals(searchMap.get("planId"))) {
                    predicateList.add(cb.equal(root.get("planId").as(String.class), Func.toInt(searchMap.get("planId"))));
                }
                //子方案流程状态
                if (searchMap.get("flow") != null) {
                    predicateList.add(cb.equal(root.get("flow").as(String.class), searchMap.get("flow")));
                }
                //子方案 多个流程状态
                if (searchMap.get("flows") != null) {
                    List<String> flows = (List<String>) searchMap.get("childPlanIds");
                    CriteriaBuilder.In<Object> in = cb.in(root.get("flow"));
                    for (String flow : flows) {
                        in.value(Byte.parseByte(flow));
                    }
                    predicateList.add(in);
                }

                if (searchMap.get("childPlanIds") != null) {
                    List<String> ids = (List<String>) searchMap.get("childPlanIds");
                    //Map ids = (Map) searchMap.get("ids");
                    CriteriaBuilder.In<Object> in = cb.in(root.get("childPlanCode"));
                    for (String id : ids) {
                        in.value(Integer.parseInt(id));
                    }
                    predicateList.add(in);
                }

                // 子方案名称
                if (searchMap.get("childPlanName") != null && !"".equals(searchMap.get("childPlanName"))) {
                    predicateList.add(cb.like(root.get("childPlanName").as(String.class), "%" + (String) searchMap.get("childPlanName") + "%"));
                }
                // 经手人(平台加前缀sys_，助贷端加前缀loan_)
                if (searchMap.get("handlePerson") != null && !"".equals(searchMap.get("handlePerson"))) {
                    predicateList.add(cb.like(root.get("handlePerson").as(String.class), "%" + (String) searchMap.get("handlePerson") + "%"));
                }
                // 其他
                if (searchMap.get("other") != null && !"".equals(searchMap.get("other"))) {
                    predicateList.add(cb.like(root.get("other").as(String.class), "%" + (String) searchMap.get("other") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 根据方案编号查询
     *
     * @param childPlanCode
     * @return
     */
    public ActionChildPlanVO findByChildPlanCode(String childPlanCode) {
        if (StringUtils.isEmpty(childPlanCode)) {
            return new ActionChildPlanVO();
        }
        ActionChildPlan actionChildPlan = actionChildPlanDao.findByChildPlanCode(childPlanCode);
        // 构造VO
        ActionChildPlanVO actionChildPlanVO = transferToVO(actionChildPlan);
        return actionChildPlanVO;
    }

    /**
     * 更新状态
     *
     * @param code
     * @param status
     */
    public void updateStatus(String code, Byte status) {
        actionChildPlanDao.updateStatus(ActionChildPlan.class, code, status);
    }

    /**
     * 批量删除
     *
     * @param toStrList
     * @return
     */
    public Boolean removeByIds(List<String> toStrList) {
        return actionChildPlanDao.removeBatch(ActionChildPlan.class, toStrList, SqlConstant.UPDATE);
    }

    /**
     * 资料审核
     *
     * @param code
     */
    public List<ExtFileData> dataVerification(String code) {
        List<ExtFileData> extFileDataList = extFileDataDao.findAllByChildPlanCode(code);
        return extFileDataList;
    }

    /**
     * 更新资料流程
     *
     * @param extFileData
     */
    public void updateDataFlowById(ExtFileData extFileData) {
        if (Objects.isNull(extFileData) || StringUtils.isEmpty(extFileData.getId())) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        extFileDataDao.update(extFileData, extFileData.getId());
    }

    /**
     * 风控审核
     *
     * @param planId
     * @return
     */
    public List<ActionChildPlanVO> riskControlAudit(Integer planId) {
        if (StringUtils.isEmpty(planId)) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        List<ActionChildPlan> allByPlanId = actionChildPlanDao.findAllByPlanId(planId);
        List<ActionChildPlanVO> collect = allByPlanId.stream().map(ActionChildPlanService::transferToVO).collect(Collectors.toList());
        return collect;
    }


    /**
     * 风控审核
     *
     * @param planId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageResult<ActionChildPlanVO> findAllChildForPage(Integer planId, int pageNo, int pageSize) {
        HashMap hashMap = new HashMap();
        hashMap.put("planId", planId);
        Page<ActionChildPlan> search = this.findSearch(hashMap, pageNo, pageSize);
        List<ActionChildPlanVO> collect = search.getContent().stream().map(ActionChildPlanService::transferToVO).collect(Collectors.toList());
        PageResult<ActionChildPlanVO> pageResult = new PageResult<>(search.getTotalElements(), collect);
        return pageResult;
    }

    /**
     * 修改风控审核状态
     *
     * @param actionChildPlan
     */
    public void updateRiskAuditStatusByCode(ActionChildPlan actionChildPlan) {
        RiskCheckResult riskCheckResult = new RiskCheckResult();
        BeanUtils.copyProperties(actionChildPlan, riskCheckResult);
        // 插入风控审核表
        riskCheckResultDao.save(riskCheckResult);
        // 更新子执行方案表 存入最新的审核结果和审核状态
        actionChildPlanDao.updateRiskAuditStatusByCode(actionChildPlan.getChildPlanCode(), actionChildPlan.getAuditStatus(), actionChildPlan.getAuditOpinion());
    }

    /**
     * 提交风控审核
     *
     * @param code
     */
    public void commitRiskAudit(String code) {
        // TODO 检查条件是否满足
        // 更新流程到 完善资料
        this.updateChildPlanFlowByCode(RisesinConstant.AGENT_APPROVAL, code);
        // TODO 流转到助贷端
    }

    /**
     * 更新子执行方案流程
     *
     * @param flow 流程
     * @param code 子方案id
     */
    public void updateChildPlanFlowByCode(byte flow, String code) {
        if (StringUtils.isEmpty(code) && StringUtils.isEmpty(flow)) {
            throw new ServiceException(ResultCode.PARAM_MISS);
        }
        // 根据id修改流程状态
        actionChildPlanDao.updateChildPlanFlow(flow, code);
    }

    /**
     * 创建订单
     *
     * @param sysOrderVO
     */
    public void createOrder(SysOrderVO sysOrderVO) {
        sysOrderServiceImpl.add(sysOrderVO);
    }

    /**
     * 查询为融资完成的子执行方案
     *
     * @param planId
     * @return
     */

    public List<ActionChildPlan> getIncompleteChildPlanInfo(Integer planId) {
        return actionChildPlanDao.findActionChildPlansByPlanIdAndFlowNotInAndStatus(planId, INCOMPLETEFLOW, (byte) 0);
    }

    /**
     * 根据收费查询子执行方案
     *
     * @param childPlanCodes
     * @return
     */
    public List<ActionChildPlanResult> findActionChildPlanByFeeDetails(List<String> childPlanCodes) {
        return actionChildPlanDao.findActionChildPlanByFeeDetails(childPlanCodes);
    }

    /**
     * bean转换成VO
     *
     * @param actionChildPlan
     * @return
     */
    public static ActionChildPlanVO transferToVO(ActionChildPlan actionChildPlan) {
        // 构造VO
        ActionChildPlanVO actionChildPlanVO = new ActionChildPlanVO();
        // 处理收费项
        if (!StringUtils.isEmpty(actionChildPlan.getOtherCharges())) {
            List<Map<String, String>> chargesList = JsonUtil.parse(actionChildPlan.getOtherCharges(), List.class);
            actionChildPlanVO.setOtherChargesVo(chargesList);
        }
        BeanUtils.copyProperties(actionChildPlan, actionChildPlanVO);
        return actionChildPlanVO;
    }

    /**
     * VO 转换 bean
     *
     * @param actionChildPlanVO
     * @return
     */
    public static ActionChildPlan transferToBean(ActionChildPlanVO actionChildPlanVO) {
        ActionChildPlan actionChildPlan = new ActionChildPlan();
        BeanUtils.copyProperties(actionChildPlanVO, actionChildPlan);
        // 处理收费项
        if (!StringUtils.isEmpty(actionChildPlanVO.getOtherCharges())) {
            // 其他收费项转json
            actionChildPlan.setOtherCharges(JsonUtil.toJson(actionChildPlanVO.getOtherChargesVo()));
        }
        // 处理利息和本金
        if (!StringUtils.isEmpty(actionChildPlanVO.getCalculateInterestMap())) {
            // 其他收费项转json
            actionChildPlan.setCalculateInterest(JsonUtil.toJson(actionChildPlanVO.getCalculateInterestMap()));
        }
        return actionChildPlan;
    }

    /**
     * C端终审额度确认
     *
     * @param actionChildPlan
     */
    public void confirmAmount(ActionChildPlan actionChildPlan) {
        if (actionChildPlan.getAccept()) {
            //接受额度,修改状态 -> 客户额度确认(8)
            actionChildPlan.setFlow(RisesinConstant.CUSTOMER_CONFIRM);
        } else {
            //拒绝额度,修改状态 -> 关闭(11)
            actionChildPlan.setFlow(RisesinConstant.CLOSE);
        }
        actionChildPlanDao.update(actionChildPlan, actionChildPlan.getChildPlanCode());
    }
}
