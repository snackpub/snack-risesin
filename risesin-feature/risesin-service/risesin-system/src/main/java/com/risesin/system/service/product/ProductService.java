package com.risesin.system.service.product;

import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.jackson.JsonUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SnowflakeIdWorker;
import com.risesin.system.dao.product.ProductAgencyDao;
import com.risesin.system.dao.product.ProductApplyConditionDao;
import com.risesin.system.dao.product.ProductDao;
import com.risesin.systemapi.dict.entity.DataTemplate;
import com.risesin.systemapi.product.entity.Product;
import com.risesin.systemapi.product.entity.ProductAgency;
import com.risesin.systemapi.product.entity.ProductApplyCondition;
import com.risesin.systemapi.product.interfaceresult.ProductNames;
import com.risesin.systemapi.product.vo.ProductApplyConditionVO;
import com.risesin.systemapi.product.vo.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * product服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class ProductService {

    // 上架
    private static final Byte SHELVES = 0;
    // 下架
    private static final Byte SOLD_OUT = -1;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductApplyConditionDao productApplyConditionDao;
    @Autowired
    private ProductAgencyDao productAgencyDao;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Product> findAll() {
        return productDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Product> findSearch(Map whereMap, int page, int size) {
        Specification<Product> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Product> findSearch(Map whereMap) {
        Specification<Product> specification = createSpecification(whereMap);
        return productDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public ProductVO findById(Integer id) {
        // 产品
        Product product = productDao.findById(Product.class, id);
        // 准入条件
        ProductApplyCondition applyCondition = productApplyConditionDao.findById(ProductApplyCondition.class, product.getFk_application_condition());
        ProductApplyConditionVO productApplyConditionVO = transferPacVO(applyCondition);

        // 产品视图对象
        ProductVO productVo = transferProVO(product);
        // 申请条件
        productVo.setProductApplyConditionVo(productApplyConditionVO);

        return productVo;
    }

    /**
     * 增加
     *
     * @param productVo
     */
    public void add(ProductVO productVo) {
        // 插入 产品准入条件
        ProductApplyCondition productApplyCondition = transferPac(productVo.getProductApplyConditionVo());
        // 生成id
        String productApplyConditionId = String.valueOf(snowflakeIdWorker.nextId());
        productApplyCondition.setId(productApplyConditionId);
        productApplyConditionDao.save(productApplyCondition);
        // VO-->bean
        Product product = transferPro(productVo);
        // 设置产品准入条件id
        product.setFk_application_condition(productApplyConditionId);
        // 保存
        productDao.save(product);
    }

    /**
     * 修改
     */
    @Transactional
    public void update(ProductVO productVo) {
        // 获取产品对象
        Product product = transferPro(productVo);
        // 更新产品
        productDao.update(product, productVo.getId());
        // 获取申请条件
        ProductApplyCondition productApplyCondition = transferPac(productVo.getProductApplyConditionVo());
        String application_condition = productVo.getProductApplyConditionVo().getId();
        if (StringUtils.isEmpty(application_condition)) {
            application_condition = productDao.findById(Product.class, productVo.getId()).getFk_application_condition();
        }
        productApplyCondition.setId(application_condition);
        // 更新申请条件
        productApplyConditionDao.update(productApplyCondition, application_condition);
    }

    /**
     * 产品授权
     *
     * @param productId     产品id
     * @param loanAgencyIds 机构ids
     * @return bool
     */
    public boolean grant(Integer productId, List<Integer> loanAgencyIds) {
        // 删除产品配置的授权机构 TODO
        productAgencyDao.removeByProductId(productId);
        // 组装配置
        List<ProductAgency> productAgencies = new ArrayList<>();
        loanAgencyIds.forEach(loanAgencyId -> {
            ProductAgency productAgency = new ProductAgency();
            productAgency.setProductId(productId);
            productAgency.setLoanAgencyId(loanAgencyId);
            productAgencies.add(productAgency);
        });
        productAgencyDao.saveBatch(productAgencies);
        return true;
    }


    public List<ProductNames> getProductNames() {
        return productDao.getProductNames(SHELVES);
    }


    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Product> createSpecification(Map searchMap) {

        return new Specification<Product>() {


            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 产品名称
                if (searchMap.get("productName") != null && !"".equals(searchMap.get("productName"))) {
                    predicateList.add(cb.like(root.get("productName").as(String.class), "%" + (String) searchMap.get("productName") + "%"));
                }
                // 产品描述
                if (searchMap.get("productDescribe") != null && !"".equals(searchMap.get("productDescribe"))) {
                    predicateList.add(cb.like(root.get("productDescribe").as(String.class), "%" + (String) searchMap.get("productDescribe") + "%"));
                }
                // 收费标准
                if (searchMap.get("chargeStandard") != null && !"".equals(searchMap.get("chargeStandard"))) {
                    predicateList.add(cb.like(root.get("chargeStandard").as(String.class), "%" + (String) searchMap.get("chargeStandard") + "%"));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.equal(root.get("id").as(String.class), Func.toInt(searchMap.get("id"))));
                }
                // status
                if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.equal(root.get("status").as(String.class), Func.toInt(searchMap.get("status"))));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public void updateStatusById(Integer id, byte status) {
        productDao.updateStatus(Product.class, id, status);
    }

    public Boolean removeByIds(String ids) {
        return productDao.removeBatch(Product.class, Func.toIntList(ids), SqlConstant.UPDATE);
    }

    /**
     * 根据产品id 查询授权机构
     *
     * @param id
     * @return
     */
    public String findLoanAgencyIdsById(Integer id) {
        // 根据id查询授权机构
        ProductAgency productAgency = new ProductAgency();
        productAgency.setProductId(id);
        Example<ProductAgency> example = Example.of(productAgency);
        List<ProductAgency> agencies = productAgencyDao.findAll(example);
        // 构造字符串
        List<String> agenyIds = new ArrayList<>();
        agencies.forEach(pa -> agenyIds.add(String.valueOf(pa.getLoanAgencyId())));
        String strAgenyIds = StringUtils.collectionToDelimitedString(agenyIds, ",");
        return strAgenyIds;
    }

    /**
     * ProductApplyConditionVo 转换为 ProductApplyCondition
     *
     * @param productApplyConditionVo
     * @return
     */
    private ProductApplyCondition transferPac(ProductApplyConditionVO productApplyConditionVo) {
        ProductApplyCondition productApplyCondition = new ProductApplyCondition();
        BeanUtils.copyProperties(productApplyConditionVo, productApplyCondition);

        if (!Objects.isNull(productApplyConditionVo.getApplicableCareerList())) {
            // 可选职业
            productApplyCondition.setApplicableCareer(JsonUtil.toJson(productApplyConditionVo.getApplicableCareerList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getExcludeIndustryList())) {
            // 排除行业
            productApplyCondition.setExcludeIndustry(JsonUtil.toJson(productApplyConditionVo.getExcludeIndustryList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getAreaList())) {
            // 可选地区
            productApplyCondition.setArea(JsonUtil.toJson(productApplyConditionVo.getAreaList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getBusinessRegistrationPlaceList())) {
            // 企业注册地
            productApplyCondition.setBusinessRegistrationPlace(JsonUtil.toJson(productApplyConditionVo.getBusinessRegistrationPlaceList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getCitizenInternationalList())) {
            // 公民国籍
            productApplyCondition.setCitizenInternational(JsonUtil.toJson(productApplyConditionVo.getCitizenInternationalList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getExcludeCreditCardStatusList())) {
            // 排除信用卡状态
            productApplyCondition.setExcludeCreditCardStatus(JsonUtil.toJson(productApplyConditionVo.getExcludeCreditCardStatusList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getExcludeCreditStatusList())) {
            // 排除征信报告状态
            productApplyCondition.setExcludeCreditStatus(JsonUtil.toJson(productApplyConditionVo.getExcludeCreditStatusList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getRestrictedLandList())) {
            // 限制项目用地
            productApplyCondition.setRestrictedLand(JsonUtil.toJson(productApplyConditionVo.getRestrictedLandList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getNearlyOneYearTaxRatingList())) {
            // 近一年评级
            productApplyCondition.setNearlyOneYearTaxRating(JsonUtil.toJson(productApplyConditionVo.getNearlyOneYearTaxRatingList()));
        }
        if (!Objects.isNull(productApplyConditionVo.getExcludeLoanCategoryList())) {
            // 排除贷款五级分类
            productApplyCondition.setExcludeLoanCategory(JsonUtil.toJson(productApplyConditionVo.getExcludeLoanCategoryList()));
        }
        return productApplyCondition;
    }

    /**
     * ProductApplyCondition 转换为 ProductApplyConditionVO
     *
     * @param productApplyCondition
     * @return
     */
    private ProductApplyConditionVO transferPacVO(ProductApplyCondition productApplyCondition) {
        ProductApplyConditionVO productApplyConditionVO = new ProductApplyConditionVO();
        BeanUtils.copyProperties(productApplyCondition, productApplyConditionVO);
        if (!StringUtils.isEmpty(productApplyCondition.getApplicableCareer())) {
            // 设置适用职业
            productApplyConditionVO.setApplicableCareerList(JsonUtil.parse(productApplyCondition.getApplicableCareer(), List.class));
        }
        if (!StringUtils.isEmpty(productApplyCondition.getArea())) {
            // 设置可选地区
            productApplyConditionVO.setAreaList(JsonUtil.parse(productApplyCondition.getArea(), List.class));
        }
        if (!StringUtils.isEmpty(productApplyCondition.getExcludeIndustry())) {
            // 设置排除职业
            productApplyConditionVO.setExcludeIndustryList(JsonUtil.parse(productApplyCondition.getExcludeIndustry(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getBusinessRegistrationPlace())) {
            // 企业注册地
            productApplyConditionVO.setBusinessRegistrationPlaceList(JsonUtil.parse(productApplyCondition.getBusinessRegistrationPlace(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getCitizenInternational())) {
            // 公民国籍
            productApplyConditionVO.setCitizenInternationalList(JsonUtil.parse(productApplyCondition.getCitizenInternational(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getExcludeCreditCardStatus())) {
            // 排除信用卡状态
            productApplyConditionVO.setExcludeCreditCardStatusList(JsonUtil.parse(productApplyCondition.getExcludeCreditCardStatus(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getExcludeCreditStatus())) {
            // 排除征信报告状态
            productApplyConditionVO.setExcludeCreditStatusList(JsonUtil.parse(productApplyCondition.getExcludeCreditStatus(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getRestrictedLand())) {
            // 限制项目用地
            productApplyConditionVO.setRestrictedLandList(JsonUtil.parse(productApplyCondition.getRestrictedLand(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getNearlyOneYearTaxRating())) {
            // 近一年评级
            productApplyConditionVO.setNearlyOneYearTaxRatingList(JsonUtil.parse(productApplyCondition.getNearlyOneYearTaxRating(), List.class));
        }
        if (!Objects.isNull(productApplyCondition.getExcludeLoanCategory())) {
            // 排除贷款五级分类
            productApplyConditionVO.setExcludeLoanCategoryList(JsonUtil.parse(productApplyCondition.getExcludeLoanCategory(), List.class));
        }

        return productApplyConditionVO;
    }

    /**
     * ProductVO 转 product
     *
     * @param productVO
     * @return
     */
    private Product transferPro(ProductVO productVO) {
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        if (!Objects.isNull(productVO.getChargeItemList())) {
            // 设置收费项
            product.setChargeItems(JsonUtil.toJson(productVO.getChargeItemList()));
        }
        if (!Objects.isNull(productVO.getDataTemplateList())) {
            // 设置模板资料
            product.setApplyTemplateData(JsonUtil.toJson(productVO.getDataTemplateList()));
        }
        if (!Objects.isNull(productVO.getFinancingMethod())) {
            // 融资方式
            product.setFinancingMethodJson(JsonUtil.toJson(productVO.getFinancingMethod()));
        }
        return product;
    }

    /**
     * product 转 ProductVO
     *
     * @param product
     * @return
     */
    private ProductVO transferProVO(Product product) {
        // 产品视图对象
        ProductVO productVo = new ProductVO();
        BeanUtils.copyProperties(product, productVo);
        if (!StringUtils.isEmpty(product.getApplyTemplateData())) {
            // 资料模板
            productVo.setDataTemplateList(JsonUtil.parseArray(product.getApplyTemplateData(), DataTemplate.class));
        }
        if (!StringUtils.isEmpty(product.getChargeItems())) {
            // 收费项
            productVo.setChargeItemList(JsonUtil.parse(product.getChargeItems(), List.class));
        }
        if (!StringUtils.isEmpty(product.getFinancingMethodJson())) {
            // 融资方式
            productVo.setFinancingMethod(JsonUtil.parse(product.getFinancingMethodJson(), List.class));
        }
        return productVo;
    }

}
