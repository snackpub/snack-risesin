package com.risesin.system.service.plan.impl;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.DateUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.*;
import com.risesin.system.service.flowlog.TransferRecordService;
import com.risesin.system.service.plan.IActionPlanService;
import com.risesin.systemapi.enterprise.dto.EnterpriseUserDTO;
import com.risesin.systemapi.feign.IEnterpriseUserClient;
import com.risesin.systemapi.flowlog.entity.TransferRecord;
import com.risesin.systemapi.message.entity.MsgCenter;
import com.risesin.systemapi.message.feign.IMsgClient;
import com.risesin.systemapi.plan.bo.AdditionalCustomerMaterialsBO;
import com.risesin.systemapi.plan.entity.*;
import com.risesin.systemapi.plan.vo.ActionPlanCustomInfoVO;
import com.risesin.systemapi.plan.vo.IncompletePlanVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * actionPlan服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class ActionPlanServiceImpl extends RisesinBaseServiceImpl<ActionPlanDao, ActionPlan, Integer> implements IActionPlanService {

    // 方案状态：申请
    private static final Byte FLOW0 = 0;
    // 方案状态：进行中
    private static final Byte FLOW1 = 1;
    // 方案状态：已放款
    private static final Byte FLOW2 = 2;
    // 方案状态：已关闭
    private static final Byte FLOW3 = 3;
    // 方案状态：申请(0);进行中(1);已完成(2);已关闭(3)
    private static final byte[] FLOW01 = {0, 1};

    /**
     * 子执行服务
     */
    //private ActionChildPlanService actionChildPlanService;
    /**
     * 企业信息
     */
    private EnterpriseInfoDao enterpriseInfoDao;
    /**
     * 融资预案
     */
    private FinancingPlanDao financingPlanDao;
    /**
     * 融资需求
     */
    private FinancingDemandDao financingDemandDao;
    /**
     * 法人基本信息
     */
    private LegalRepresentativeDao legalRepresentativeDao;
    /**
     * 企业资产信息
     */
    private EnterpriseAssetInfoDao assetInfoDao;
    /**
     * 企业经营信息
     */
    private BusinessInfoDao businessInfoDao;

    /**
     * 企业征信
     */
    private EnterpriseCreditInfoDao creditInfoDao;

    /**
     * 企业用户
     */
    private IEnterpriseUserClient enterpriseUserClient;

    /**
     * 经手人流转记录
     */
    private TransferRecordService transferRecordService;

    private IMsgClient msgClient;


    @Override
    public Page<ActionPlan> findSearch(Map whereMap, Query query) {
        Specification<ActionPlan> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public ActionPlan findById(Integer id) {
        return baseDao.findById(ActionPlan.class, id);
    }

    @Override
    public ActionPlan findById(String planCode) {
        return baseDao.findActionPlanByPlanCode(planCode);
    }


    @Override
    public void add(ActionPlan actionPlan) {
        baseDao.save(actionPlan);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap 参数
     * @return Specification<ActionPlan>
     */
    private Specification<ActionPlan> createSpecification(Map searchMap) {

        return new Specification<ActionPlan>() {

            @Override
            public Predicate toPredicate(Root<ActionPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                // 企业用户id
                if (searchMap.get("enterpriseUserId") != null && !"".equals(searchMap.get("enterpriseUserId"))) {
                    predicateList.add(cb.equal(root.get("entUserId").as(String.class), searchMap.get("enterpriseUserId")));
                }

                //执行方案 ids(集合)
                if (searchMap.get("ids") != null && ((List<String>) searchMap.get("ids")).size() > 0) {
                    List<String> ids = (List<String>) searchMap.get("ids");
                    CriteriaBuilder.In<Object> in = cb.in(root.get("id"));
                    for (String id : ids) {
                        in.value(Integer.parseInt(id));
                    }
                    predicateList.add(in);
                }

                // 流程状态：申请(0);进行中(1);已完成(2);已关闭(3)
                if (searchMap.get("flow") != null) {
                    predicateList.add(cb.equal(root.get("flow").as(String.class), searchMap.get("flow")));
                }

                // 融资主体（公司名）
                if (searchMap.get("companyName") != null && !"".equals(searchMap.get("companyName"))) {
                    predicateList.add(cb.like(root.get("companyName").as(String.class), "%" + (String) searchMap.get("companyName") + "%"));
                }

                // 执行方案编号
                if (searchMap.get("planCode") != null && !"".equals(searchMap.get("planCode"))) {
                    predicateList.add(cb.like(root.get("planCode").as(String.class), "%" + (String) searchMap.get("planCode") + "%"));
                }
                // 执行方案名称
                if (searchMap.get("planName") != null && !"".equals(searchMap.get("planName"))) {
                    predicateList.add(cb.like(root.get("planName").as(String.class), "%" + (String) searchMap.get("planName") + "%"));
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


    @Override
    public List<IncompletePlanVO> get6IncompleteActionPlanInfo(String enterpriseUserId) {
        return baseDao.findTop6ByEntUserIdAndFlowIsInAndStatusOrderByCreateTimeDesc(enterpriseUserId, FLOW01, SqlConstant.START_USEING)
                .stream().map(actionPlan -> {
                    IncompletePlanVO incompletePlanVO = new IncompletePlanVO();
                    incompletePlanVO.setId(actionPlan.getId()); //执行方案id
                    incompletePlanVO.setCreateTime(actionPlan.getCreateTime()); //执行方案创建时间
                    incompletePlanVO.setPlanName(actionPlan.getPlanName()); //执行方案名称
                    incompletePlanVO.setFlow(actionPlan.getFlow()); //执行方案状态
                    FinancingPlan financingPlan = financingPlanDao.findById(FinancingPlan.class, actionPlan.getFiancingPlanId()); //融资预案信息
                    incompletePlanVO.setFinancingAmount(financingDemandDao.findById(financingPlan.getFinDemandId()).get().getFinancingAmount()); //融资金额
                    incompletePlanVO.setCompanyName(enterpriseInfoDao.findById(financingPlan.getEntInfoId()).get().getName()); //公司名称
                    return incompletePlanVO;
                }).collect(Collectors.toList());
    }

    /**
     * 未完成的父执行方案列表(C端调用)
     *
     * @param enterpriseUserId 企业用户ID
     * @return
     */
    @Override
    public List<ActionPlan> getIncompleteActionPlanInfo(String enterpriseUserId) {
        return baseDao.findActionPlansByEntUserIdAndFlowIsInAndStatusOrderByCreateTimeDesc(enterpriseUserId, FLOW01, SqlConstant.START_USEING);
    }

    @Override
    public AdditionalCustomerMaterialsBO additionalCustomerMaterials(String financingCode) {
        // 根据客户ID融资预案信息
        FinancingPlan financingPlan = financingPlanDao.findByFinancingCode(financingCode);
//        // 企业用户信息
//        EnterpriseUserDTO userDTO = enterpriseUserClient.findEnterpriseById(financingPlan.getEntUserId()).getData();
        // 查询融资需求
        FinancingDemand financingDemand = SqlHelper.optional(financingDemandDao.findById(financingPlan.getFinDemandId()));
        // 公司基本信息
        EnterpriseInfo enterpriseInfo = SqlHelper.optional(enterpriseInfoDao.findById(financingPlan.getEntInfoId()));
        // 法人基本信息
        LegalRepresentative representative = SqlHelper.optional(legalRepresentativeDao.findById(financingPlan.getLegRepId()));
        // 企业资产信息
        EnterpriseAssetInfo assetInfo = SqlHelper.optional(assetInfoDao.findById(financingPlan.getEntAssetId()));
        // 企业营业信息
        BusinessInfo businessInfo = SqlHelper.optional(businessInfoDao.findById(financingPlan.getBusinessId()));
        // 客户征信数据
        EnterpriseCreditInfo creditInfo = SqlHelper.optional(creditInfoDao.findById(financingPlan.getEntCreditId()));
        return new AdditionalCustomerMaterialsBO(financingPlan, financingDemand, enterpriseInfo, representative, assetInfo, businessInfo, creditInfo);
    }


    @Override
    public boolean saveAdditionalCustomerMaterials(AdditionalCustomerMaterialsBO customerMaterialsBO) {
        // 融资预案信息
        FinancingPlan financingPlan = customerMaterialsBO.getFinancingPlan();
        financingPlanDao.update(financingPlan, financingPlan.getId());
//        // 企业用户信息
//        EnterpriseUserDTO userDTO = customerMaterialsBO.getUserDTO();
//        enterpriseUserClient.updateEnterpriseUser(customerMaterialsBO.getUserDTO());
        // 融资需求信息
        FinancingDemand financingDemand = customerMaterialsBO.getFinancingDemand();
        financingDemandDao.update(financingDemand, financingDemand.getId());
        // 公司基本信息
        EnterpriseInfo enterpriseInfo = customerMaterialsBO.getEnterpriseInfo();
        enterpriseInfoDao.update(enterpriseInfo, enterpriseInfo.getId());
        // 法人基本信息
        LegalRepresentative representative = customerMaterialsBO.getRepresentative();
        legalRepresentativeDao.update(representative, representative.getId());
        // 企业资产信息
        EnterpriseAssetInfo assetInfo = customerMaterialsBO.getAssetInfo();
        assetInfoDao.update(assetInfo, assetInfo.getId());
        // 企业营业信息
        BusinessInfo businessInfo = customerMaterialsBO.getBusinessInfo();
        businessInfoDao.update(businessInfo, businessInfo.getId());
        // 客户征信数据
        EnterpriseCreditInfo creditInfo = customerMaterialsBO.getCreditInfo();
        creditInfoDao.update(creditInfo, creditInfo.getId());
        // 增加执行方案
        ActionPlan plan = new ActionPlan();
        String date = DateUtil.formatDate(new Date()).replace("-", "");
        String planName = enterpriseInfo.getName() + "融资方案" + date;
        plan.setPlanName(planName);
        plan.setPlanCode(SnowflakeIDGenerator.idGenerator());
//        plan.setEntUserId(userDTO.getId());
        plan.setFiancingPlanId(financingPlan.getId());
        if (customerMaterialsBO.getFlow().equals(FLOW1)) {
            plan.setFlow(FLOW1);
        } else {
            // 默认状态为申请(0)
            plan.setFlow(FLOW0);
        }
        plan.setStatus(SqlConstant.START_USEING);
        baseDao.save(plan);
        // 增加到消息中心
        MsgCenter msgCenter = new MsgCenter();
        msgCenter.setMsgTitle(planName + "客户资料已完善");
        msgCenter.setMsgContent(planName + "客户资料已完善");
        msgCenter.setMsgType(1);
//        msgCenter.setChildPlanCode(""); // todo
        msgClient.add(msgCenter);
        // 经手人操作流转记录
        TransferRecord transferRecord = TransferRecord.builder()
                .handleBy("sys_" + 0) // TODO 该用户需要从权限框架中全局获取
                .handleByName("经手人")   // TODO 该用户需要从权限框架中全局获取
                .isCurrent((byte) 1) // TODO 改为int
                .planCode("plan_" + plan.getPlanCode())
                .flow(1) // TODO 流转记录
                .content("某某完善了执行方案") // 用户名
                .build();
        transferRecordService.add(transferRecord);
        return true;
    }

    @Override
    public boolean updateAdditionalCustomerMaterials(ActionPlanCustomInfoVO actionPlanCustomInfoVO) {
        // 融资预案信息
//        FinancingPlan financingPlan = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getFinancingPlan();
//        financingPlanDao.update(financingPlan, financingPlan.getId());
        // 融资方案信息
        ActionPlan actionPlanVO = actionPlanCustomInfoVO.getActionPlan();
        baseDao.update(actionPlanVO, actionPlanVO.getId());
//        // 企业用户信息
//        EnterpriseUserDTO userDTO = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getUserDTO();
//        enterpriseUserClient.updateEnterpriseUser(userDTO);
        // 融资需求信息
        FinancingDemand financingDemand = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getFinancingDemand();
        financingDemandDao.update(financingDemand, financingDemand.getId());
        // 公司基本信息
        EnterpriseInfo enterpriseInfo = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getEnterpriseInfo();
        enterpriseInfoDao.update(enterpriseInfo, enterpriseInfo.getId());
        // 法人基本信息
        LegalRepresentative representative = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getRepresentative();
        legalRepresentativeDao.update(representative, representative.getId());
        // 企业资产信息
        EnterpriseAssetInfo assetInfo = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getAssetInfo();
        assetInfoDao.update(assetInfo, assetInfo.getId());
        // 企业营业信息
        BusinessInfo businessInfo = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getBusinessInfo();
        businessInfoDao.update(businessInfo, businessInfo.getId());
        // 客户征信数据
        EnterpriseCreditInfo creditInfo = actionPlanCustomInfoVO.getAdditionalCustomerMaterials().getCreditInfo();
        creditInfoDao.update(creditInfo, creditInfo.getId());
        // 经手人操作流转记录
        TransferRecord transferRecord = TransferRecord.builder()
                .handleBy(0 + "") // TODO 该用户需要从权限框架中全局获取
                .handleByName("经手人")   // TODO 该用户需要从权限框架中全局获取
                .isCurrent((byte) 1) // TODO 改为int
                .planCode("plan_" + actionPlanVO.getPlanCode())
                .flow(1) // TODO 流转记录
                .content("某某修改了执行方案") // 用户名
                .build();
        transferRecordService.add(transferRecord);
        return true;
    }

    @Override
    public ActionPlanCustomInfoVO selectActionPlanInfo(String planCode) {
        ActionPlan actionPlan1 = this.findById(planCode);
        FinancingPlan optional = SqlHelper.optional(financingPlanDao.findById(actionPlan1.getFiancingPlanId()));
        // 获取客户完善的资料
        AdditionalCustomerMaterialsBO additionalCustomerMaterialsBO = this.additionalCustomerMaterials(optional.getFinancingCode());
        ActionPlanCustomInfoVO build = ActionPlanCustomInfoVO.builder().
                actionPlan(actionPlan1).
                additionalCustomerMaterials(additionalCustomerMaterialsBO).build();
        return build;
    }


    @Override
    public int getTopBar(String enterpriseUserId) {
        return baseDao.countByEntUserIdAndFlowIsInAndStatus(enterpriseUserId, FLOW01, SqlConstant.START_USEING);
    }
}
