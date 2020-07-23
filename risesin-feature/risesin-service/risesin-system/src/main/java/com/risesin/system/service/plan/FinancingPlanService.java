package com.risesin.system.service.plan;

import com.risesin.core.jpaplus.generator.SnowflakeIDGenerator;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.DateUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.plan.*;
import com.risesin.system.dao.product.ProductDao;
import com.risesin.systemapi.plan.bo.FinancingApplication;
import com.risesin.systemapi.plan.entity.*;
import com.risesin.systemapi.plan.vo.EnterpriseAssetInfoVO;
import com.risesin.systemapi.plan.vo.EnterpriseCreditInfoVO;
import com.risesin.systemapi.plan.vo.FinancingDemandVO;
import com.risesin.systemapi.product.entity.Product;
import com.risesin.systemapi.product.entity.ProductApplyCondition;
import com.risesin.systemapi.product.vo.QueryResult;
import lombok.AllArgsConstructor;
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
 * financingPlan服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class FinancingPlanService {

    private static final byte UNCONSULT = 1;

    private FinancingPlanDao financingPlanDao;

    private ExcludeFinancingTypeDao excludeFinancingTypeDao;

    private FinancingDemandDao financingDemandDao;

    private EnterpriseInfoDao enterpriseInfoDao;

    private LegalRepresentativeDao legalRepresentativeDao;

    private EnterpriseAssetInfoDao enterpriseAssetInfoDao;

    private BusinessInfoDao businessInfoDao;

    private EnterpriseCreditInfoDao enterpriseCreditInfoDao;

    private ProductDao productDao;

    public List<FinancingPlan> findAll() {
        return financingPlanDao.findAll();
    }


    public Page<FinancingPlan> findSearch(Map whereMap, Query query) {
        Specification<FinancingPlan> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return financingPlanDao.findAll(specification, pageRequest);
    }


    public List<FinancingPlan> findSearch(Map whereMap) {
        Specification<FinancingPlan> specification = createSpecification(whereMap);
        return financingPlanDao.findAll(specification);
    }


    public FinancingPlan findById(Integer pkId) {
        return SqlHelper.optional(financingPlanDao.findById(pkId));
    }


    public void add(FinancingPlan financingPlan) {
        financingPlanDao.save(financingPlan);
    }

    public FinancingPlan save(FinancingPlan financingPlan) {
        return financingPlanDao.save(financingPlan);
    }


    public void update(FinancingPlan financingPlan) {
        financingPlanDao.update(financingPlan, financingPlan.getId());
    }

    public void deleteById(Integer id) {
        financingPlanDao.deleteById(id);
    }

    public Boolean removeBatch(List<Integer> ids) {
        return financingPlanDao.removeBatch(FinancingPlan.class, ids, SqlConstant.UPDATE);
    }

    /**
     * 排除融资方式
     *
     * @param planId 预案ID
     * @param ids    排除的方式ID
     * @return
     */
    public Boolean excludeFinancingTypes(String planId, String ids) {
        List<ExcludeFinancingType> excludeFinancingTypes = new ArrayList<>();
        Func.toIntList(ids).forEach(excludeId -> {
            ExcludeFinancingType excludeFinancingType = new ExcludeFinancingType();
            excludeFinancingType.setFinancingPlanId(Func.toInt(planId));
            excludeFinancingType.setFinancingTypeId(excludeId);
            excludeFinancingTypes.add(excludeFinancingType);
        });
        return excludeFinancingTypeDao.saveBatch(excludeFinancingTypes);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<FinancingPlan> createSpecification(Map searchMap) {

        return new Specification<FinancingPlan>() {

            @Override
            public Predicate toPredicate(Root<FinancingPlan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 融资预案code
                if (searchMap.get("financingCode") != null && !"".equals(searchMap.get("financingCode"))) {
                    predicateList.add(cb.like(root.get("financingCode").as(String.class), "%" + (String) searchMap.get("financingCode") + "%"));
                }
                // 融资主体名称
                if (searchMap.get("companyName") != null && !"".equals(searchMap.get("companyName"))) {
                    predicateList.add(cb.like(root.get("companyName").as(String.class), "%" + (String) searchMap.get("companyName") + "%"));
                }
                // 法人代表
                if (searchMap.get("legalName") != null && !"".equals(searchMap.get("legalName"))) {
                    predicateList.add(cb.like(root.get("legalName").as(String.class), "%" + (String) searchMap.get("legalName") + "%"));
                }
                //　客服名称
                if (searchMap.get("customerServiceName") != null && !"".equals(searchMap.get("customerServiceName"))) {
                    predicateList.add(cb.like(root.get("customerServiceName").as(String.class), "%" + (String) searchMap.get("customerServiceName") + "%"));
                }
                // 状态
                if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(root.get("status").as(String.class), "%" + (String) searchMap.get("status") + "%"));
                }
                //企业用户id
                if (searchMap.get("enterpriseUserId") != null && !"".equals(searchMap.get("enterpriseUserId"))) {
                    predicateList.add(cb.equal(root.get("entUserId").as(String.class), searchMap.get("enterpriseUserId")));
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

    /**
     * 根据code查询
     *
     * @param finCode
     * @return
     */
    public FinancingPlan findByCode(String finCode) {
        return financingPlanDao.findByFinancingCode(finCode);
    }

    /**
     * 融资申请
     *
     * @param financingApplication
     */
    public void addFinancingPlan(FinancingApplication financingApplication) {

        //转化
        FinancingDemand oldFinancingDemand = BeanUtil.copy(financingApplication.getFinancingDemandVO(), FinancingDemand.class);

        if (financingApplication.getFinancingDemandVO().getExcludeFinancingTypeList() != null && financingApplication.getFinancingDemandVO().getExcludeFinancingTypeList().size() > 0) {
            oldFinancingDemand.setExcludeFinancingType(JsonUtil.toJson(financingApplication.getFinancingDemandVO().getExcludeFinancingTypeList()));
        }

        EnterpriseAssetInfo oldEnterpriseAssetInfo = BeanUtil.copy(financingApplication.getEnterpriseAssetInfoVO(), EnterpriseAssetInfo.class);
        if (financingApplication.getEnterpriseAssetInfoVO().getProjectSiteList() != null && financingApplication.getEnterpriseAssetInfoVO().getProjectSiteList().size() > 0) {
            oldEnterpriseAssetInfo.setProjectSite(JsonUtil.toJson(financingApplication.getEnterpriseAssetInfoVO().getProjectSiteList()));
        }

        EnterpriseCreditInfo oldEnterpriseCreditInfo = BeanUtil.copy(financingApplication.getEnterpriseCreditInfoVO(), EnterpriseCreditInfo.class);
        if (financingApplication.getEnterpriseCreditInfoVO().getLoanClassificationList() != null && financingApplication.getEnterpriseCreditInfoVO().getLoanClassificationList().size() > 0) {
            oldEnterpriseCreditInfo.setLoanClassification(JsonUtil.toJson(financingApplication.getEnterpriseCreditInfoVO().getLoanClassificationList()));
        }
        if (financingApplication.getEnterpriseCreditInfoVO().getCreditReportStatusList() != null && financingApplication.getEnterpriseCreditInfoVO().getCreditReportStatusList().size() > 0) {
            oldEnterpriseCreditInfo.setCreditReportStatus(JsonUtil.toJson(financingApplication.getEnterpriseCreditInfoVO().getCreditReportStatusList()));
        }

        FinancingDemand financingDemand = financingDemandDao.save(oldFinancingDemand);
        EnterpriseInfo enterpriseInfo = enterpriseInfoDao.save(financingApplication.getEnterpriseInfo());
        LegalRepresentative legalRepresentative = legalRepresentativeDao.save(financingApplication.getLegalRepresentative());
        EnterpriseAssetInfo enterpriseAssetInfo = enterpriseAssetInfoDao.save(oldEnterpriseAssetInfo);
        BusinessInfo businessInfo = businessInfoDao.save(financingApplication.getBusinessInfo());
        EnterpriseCreditInfo enterpriseCreditInfo = enterpriseCreditInfoDao.save(oldEnterpriseCreditInfo);

        //赋值
        FinancingPlan financingPlan = new FinancingPlan();
        String code = SnowflakeIDGenerator.idGenerator();
        //code
        financingPlan.setFinancingCode(code);
        //预案名称
        String date = DateUtil.format(LocalDateTime.now(), "yyyyMMdd");
        //TODO userId
        String entUserId = "1";
        financingPlan.setEntUserId(entUserId);
        int finPlanNum = financingPlanDao.countByEntUserIdAndAndCompanyName(entUserId, enterpriseInfo.getName()) + 1;
        financingPlan.setName(date + enterpriseInfo.getName() + "-" + finPlanNum + "号融资预案");
        //融资主体
        financingPlan.setCompanyName(enterpriseInfo.getName());
        //融资金额
        financingPlan.setFinancingAmount(financingDemand.getFinancingAmount());
        //未咨询状态
        financingPlan.setStatus(UNCONSULT);
        //法人代表姓名
        financingPlan.setLegalName(legalRepresentative.getName());
        financingPlan.setFinDemandId(financingDemand.getId());
        financingPlan.setEntInfoId(enterpriseInfo.getId());
        financingPlan.setLegRepId(legalRepresentative.getId());
        financingPlan.setEntAssetId(enterpriseAssetInfo.getId());
        financingPlan.setBusinessId(businessInfo.getId());
        financingPlan.setEntCreditId(enterpriseCreditInfo.getId());


        pickProduct(financingApplication);
        //TODO 生成PDF
        //TODO赋值预览地址、下载地址

        financingPlanDao.save(financingPlan);
    }


    private List<QueryResult> pickProduct(FinancingApplication financingApplication) {

        //匹配项1：排除融资方式
        //获取排除融资方式后的产品列表
        List<QueryResult> productList = getProductList(financingApplication);

        //融资需求
        FinancingDemandVO financingDemandVO = financingApplication.getFinancingDemandVO();

        //企业基本信息
        EnterpriseInfo enterpriseInfo = financingApplication.getEnterpriseInfo();

        //法人代表基本信息
        LegalRepresentative legalRepresentative = financingApplication.getLegalRepresentative();

        //企业资产信息
        EnterpriseAssetInfoVO enterpriseAssetInfoVO = financingApplication.getEnterpriseAssetInfoVO();

        //企业经营信息
        BusinessInfo businessInfo = financingApplication.getBusinessInfo();

        //企业征信信息
        EnterpriseCreditInfoVO enterpriseCreditInfoVO = financingApplication.getEnterpriseCreditInfoVO();

        //产品最终结果收集器
        List<QueryResult> pickProductList = new ArrayList<>();

        //根据用户填写的信息匹配产品
        for (QueryResult queryResult : productList) {
            //产品
            Product product = queryResult.getProduct();
            //产品准入条件
            ProductApplyCondition productApplyCondition = queryResult.getProductApplyCondition();

            //匹配项1：借款周期(C端必填，咨询端必填)
            if (null == product.getLoanPeriodOther() || product.getLoanPeriodOther().intValue() <= 0) {
                //其它周期为空，进行最大周期和最小周期匹配
                if (!(financingDemandVO.getLoanPeriod() >= product.getLoanPeriodMin() && financingDemandVO.getLoanPeriod() <= product.getLoanPeriodMax())) {
                    continue;
                }
            } else {
                if (product.getLoanPeriodOther().intValue() != financingDemandVO.getLoanPeriod().intValue()) {
                    continue;
                }
            }

            //匹配项2：是否上征信(C端必填，企业端必填)
            if (product.getIsCreditInvestigation() && !financingDemandVO.getEnableCredit()) {
                continue;
            }


            //匹配项3：公司注册地
            if (null != productApplyCondition.getBusinessRegistrationPlace() && null != enterpriseInfo.getRegAddress()) {
                if (!pickJson(productApplyCondition.getBusinessRegistrationPlace(), enterpriseInfo.getRegAddress())) {
                    continue;
                }
            }

            //匹配项4：公司注册时间 TODO

            //匹配项5：公司注册资本
            if (null != productApplyCondition.getRegisteredCapital() && null != enterpriseInfo.getRegCapital()) {
                int i = productApplyCondition.getRegisteredCapital().compareTo(enterpriseInfo.getRegCapital());
                if (subtractForBoolean(enterpriseInfo.getRegCapital(), productApplyCondition.getRegisteredCapital())) {
                    continue;
                }
            }

            //匹配项6：所属行业 排除行业(C端必填，咨询端必填)
            if (pickJson(productApplyCondition.getExcludeIndustry(), enterpriseInfo.getIndustry())) {
                continue;
            }


            //匹配项7：所属地区 可选地区(C端必填，咨询端必填)
            if (!pickJson(productApplyCondition.getArea(), enterpriseInfo.getArea())) {
                continue;
            }


            //匹配项8：法人代表变更
            if (null != enterpriseInfo.getLegalReprChange() && null != productApplyCondition.getCorporationChangeTime()) {
                if ((int) enterpriseInfo.getLegalReprChange() < productApplyCondition.getCorporationChangeTime()) {
                    continue;
                }
            }

            //匹配项9：法人代表年龄(C端必填)
            if (null != legalRepresentative.getAge() && null != productApplyCondition.getApplicantAgeMin() && null != productApplyCondition.getApplicantAgeMax()) {
                if (!(productApplyCondition.getApplicantAgeMin() < legalRepresentative.getAge() && legalRepresentative.getAge() < productApplyCondition.getApplicantAgeMax())) {
                    continue;
                }
            }

            //匹配项10：法人代表占股比例(C端必填，企业端必填)
            if (subtractForBoolean(legalRepresentative.getSharerate(), productApplyCondition.getCorporationShare())) {
                continue;
            }

            //匹配项11：法人代表国籍(C端必填)
            if (null != legalRepresentative.getNationality() && null != productApplyCondition.getCitizenInternational()) {
                if (!pickJson(productApplyCondition.getCitizenInternational(), legalRepresentative.getNationality())) {
                    continue;
                }
            }

            //匹配项12：法人代表职业
            if (null != legalRepresentative.getOccupation() && null != productApplyCondition.getApplicableCareer()) {
                if (!pickJson(productApplyCondition.getApplicableCareer(), legalRepresentative.getOccupation())) {
                    continue;
                }
            }

            //匹配项13：资产总值(C端必填)  准入门条件没有资产总值

            //匹配项14：非经营性资产总值(C端必填) 准入门条件没有非经营性资产总值

            //匹配项15：申请区域是否需要房产
            if (productApplyCondition.getIsHourse() && null != enterpriseAssetInfoVO.getRealEstateNumber()) {
                if (enterpriseAssetInfoVO.getRealEstateNumber() <= 0) {
                    continue;
                }
            }

            //匹配项16：申请区域房产估值 准入门条件没有申请区域房产估值

            //匹配项17：质押物估值 准入门条件没有

            //匹配项18：应收款(C端必填) 人为匹配

            //匹配项19：是否有特别经营许可(C端必填，咨询端必填)
            if (productApplyCondition.getIsSpecialManage() && !enterpriseAssetInfoVO.getHasBusinessLicense()) {
                continue;
            }

            //匹配项20 项目用地
            if (null != enterpriseAssetInfoVO.getProjectSiteList() && enterpriseAssetInfoVO.getProjectSiteList().size() > 0
                    && null != productApplyCondition.getRestrictedLand()) {
                if (!pick2ForJson(enterpriseAssetInfoVO.getProjectSiteList(), productApplyCondition.getRestrictedLand())) {
                    continue;
                }
            }

            //匹配项21 是否允许有刑事或民事纠纷 是否有刑事或民事纠纷(C端必填，咨询端必填)
            if (!productApplyCondition.getIsCriminal() && enterpriseAssetInfoVO.getHasCase()) {
                continue;
            }

            //匹配项22 上年度营收(C端必填，咨询端必填)
            if (subtractForBoolean(businessInfo.getLastYearRevenue(), productApplyCondition.getLastYearRevenue())) {
                continue;
            }

            //匹配项23 上年度扣非营收(C端必填，咨询端必填) v1 < v2 返回true
            if (subtractForBoolean(businessInfo.getRerevenueExcludeManage(), productApplyCondition.getRerevenueExcludeManage())) {
                continue;
            }

            //匹配项24 企业负债率(C端必填，咨询端必填)
            if (subtractForBoolean(productApplyCondition.getCorporateDebt(), businessInfo.getDebtrate())) {
                continue;
            }


            //匹配项25 已有贷款 产品没有

            //匹配项26 信用卡额度使用比利 产品没有

            //匹配项27 发票开票时长

            //匹配项28 上年度开票金额

            //匹配项29 季报

            //匹配项30 本年度纳税评级

            //匹配项30 上年度纳税评级
            if (Func.isNotBlank(businessInfo.getLastYearTaxRate()) && Func.isNotBlank(productApplyCondition.getNearlyOneYearTaxRating())) {
                if (!pickJson(productApplyCondition.getNearlyOneYearTaxRating(), businessInfo.getLastYearTaxRate())) {
                    continue;
                }
            }


            //匹配项31 是否有以下贷款分类
            List<String> loanClassificationList = enterpriseCreditInfoVO.getLoanClassificationList();
            if (loanClassificationList != null && loanClassificationList.size() > 0
                    && productApplyCondition.getExcludeLoanCategory() != null) {

                if (pick2ForJson(loanClassificationList, productApplyCondition.getExcludeLoanCategory())) {
                    continue;
                }
            }


            //匹配项32 征信报告是否有以下状态
            List<String> creditReportStatusList = enterpriseCreditInfoVO.getCreditReportStatusList();
            if (creditReportStatusList != null && creditReportStatusList.size() > 0
                    && productApplyCondition.getExcludeCreditStatus() != null) {

                if (pick2ForJson(creditReportStatusList, productApplyCondition.getExcludeCreditStatus())) {
                    continue;
                }
            }

            //匹配项33 5年内逾期次数
            //if(creditOverdue5Years != null && )
            if (null != enterpriseCreditInfoVO.getCreditDueTimes5Years() && null != productApplyCondition.getCreditReportingTime()) {
                if (enterpriseCreditInfoVO.getCreditDueTimes5Years() > productApplyCondition.getCreditReportingTime()) {
                    continue;
                }
            }

            //匹配项34 最长逾期时长
            if (null != enterpriseCreditInfoVO.getLongestCreditOverdue() && null != productApplyCondition.getMaxOverdueTime()) {
                if (enterpriseCreditInfoVO.getLongestCreditOverdue() - productApplyCondition.getMaxOverdueTime() > 0) {
                    continue;
                }
            }

            //匹配项35 当前是够逾期
            if (!productApplyCondition.getHasOverdue() && enterpriseCreditInfoVO.getCurrOver()) {
                continue;
            }

            //匹配项36 信用卡是否有以下状态
            List<String> creditCardStatusList = enterpriseCreditInfoVO.getCreditCardStatusList();
            if (null != creditCardStatusList && creditCardStatusList.size() > 0
                    && productApplyCondition.getExcludeCreditStatus() != null) {

                if (pick2ForJson(creditCardStatusList, productApplyCondition.getExcludeCreditCardStatus())) {
                    continue;
                }
            }
            pickProductList.add(queryResult);
        }
        return pickProductList;
    }

    /**
     * 查询排除融资方式后的产品列表
     *
     * @param financingApplication
     * @return
     */
    private List<QueryResult> getProductList(FinancingApplication financingApplication) {
        List<String> excludeFinancingTypeList = financingApplication.getFinancingDemandVO().getExcludeFinancingTypeList();
        if (excludeFinancingTypeList != null && excludeFinancingTypeList.size() > 0) {
            //查询排除融资方式后的产品列表进行匹配
            return productDao.findExcludeTypes(excludeFinancingTypeList);
        } else {
            //查询所有产品列表进行匹配
            List list = new ArrayList();
            list.add("");
            return productDao.findExcludeTypes(list);
        }
    }

    /**
     * v1 < v2 返回true
     * 相等返回0
     *
     * @param v1
     * @param v2
     * @return
     */
    private boolean subtractForBoolean(BigDecimal v1, BigDecimal v2) {
        //int i = BigDecimalUtils.safeSubtract(v1, v2).intValue();
        //int i = safeSubtract(v1, v2).intValue();
        //boolean b = safeSubtract(v1, v2).intValue() == -1 ? true : false;
        return safeSubtract(v1, v2).intValue() == -1 ? true : false;
    }

    /**
     * 匹配上返回true
     *
     * @param jsonString
     * @param pickString
     * @return
     */
    private boolean pickJson(String jsonString, String pickString) {
        List<String> jsonList = JsonUtil.parse(jsonString, List.class);
        boolean flag = false;
        for (String singleString : jsonList) {
            if (pickString.equals(singleString)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 双层for循环匹配，匹配成功立即返回true
     *
     * @param list
     * @param jsonString2
     * @return
     */
    private boolean pick2ForJson(List<String> list, String jsonString2) {
        List<String> jsonString2List = JsonUtil.parse(jsonString2, List.class);

        boolean flag = false;
        for (String string1 : list) {
            for (String string2 : jsonString2List) {
                if (string1.equals(string2)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }

    /**
     * 计算金额方法
     *
     * @param b1
     * @param bn
     * @return
     * @author : shijing
     * 2017年3月23日下午4:53:00
     */
    public BigDecimal safeSubtract(BigDecimal b1, BigDecimal... bn) {
        return safeSubtract(true, b1, bn);
    }

    /**
     * BigDecimal的安全减法运算
     *
     * @param isZero 减法结果为负数时是否返回0，true是返回0（金额计算时使用），false是返回负数结果
     * @param b1     被减数
     * @param bn     需要减的减数数组
     * @return
     * @author : shijing
     * 2017年3月23日下午4:50:45
     */
    public BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {
        if (null == b1) {
            b1 = BigDecimal.ZERO;
        }
        BigDecimal r = b1;
        if (null != bn) {
            for (BigDecimal b : bn) {
                r = r.subtract((null == b ? BigDecimal.ZERO : b));
            }
        }

        return isZero ? (r.compareTo(BigDecimal.ZERO) == -1 ? new BigDecimal("-1") : r) : r;
    }


}
