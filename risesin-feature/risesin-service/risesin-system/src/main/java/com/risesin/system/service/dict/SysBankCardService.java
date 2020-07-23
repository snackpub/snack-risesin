package com.risesin.system.service.dict;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.dict.SysBankCardDao;
import com.risesin.systemapi.dict.entity.SysBankCard;
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
 * sysBankCard服务层
 *
 * @author Administrator
 */
@Service
public class SysBankCardService extends RisesinBaseServiceImpl<SysBankCardDao, SysBankCard, Integer> {


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<SysBankCard> findAll() {
        return baseDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param query
     * @return
     */
    public Page<SysBankCard> findSearch(Map whereMap, Query query) {
        Specification<SysBankCard> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<SysBankCard> findSearch(Map whereMap) {
        Specification<SysBankCard> specification = createSpecification(whereMap);
        return baseDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    @Override
    public SysBankCard findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    /**
     * 根据银行卡号查询
     */
    public SysBankCard findByCard(String card) {
        return baseDao.getBankCard(card);
    }

    /**
     * 增加
     *
     * @param sysBankCard
     */
    @Override
    public void add(SysBankCard sysBankCard) {
        baseDao.save(sysBankCard);
    }

    public void update(SysBankCard sysBankCard) {
        baseDao.update(sysBankCard, sysBankCard.getId());
    }


    /**
     * 删除
     *
     * @param ids
     */
    public void removeBatch(List<Integer> ids) {
        baseDao.removeBatch(SysBankCard.class, ids, SqlConstant.UPDATE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysBankCard> createSpecification(Map searchMap) {

        return new Specification<SysBankCard>() {

            @Override
            public Predicate toPredicate(Root<SysBankCard> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 银行卡号
                if (searchMap.get("card") != null && !"".equals(searchMap.get("card"))) {
                    predicateList.add(cb.like(root.get("card").as(String.class), "%" + (String) searchMap.get("card") + "%"));
                }
                // 支付名称
                if (searchMap.get("payName") != null && !"".equals(searchMap.get("payName"))) {
                    predicateList.add(cb.like(root.get("payName").as(String.class), "%" + (String) searchMap.get("payName") + "%"));
                }
                // 收款方
                if (searchMap.get("beneficiary") != null && !"".equals(searchMap.get("beneficiary"))) {
                    predicateList.add(cb.like(root.get("beneficiary").as(String.class), "%" + (String) searchMap.get("beneficiary") + "%"));
                }
                // 开户行
                if (searchMap.get("beneBank") != null && !"".equals(searchMap.get("beneBank"))) {
                    predicateList.add(cb.like(root.get("beneBank").as(String.class), "%" + (String) searchMap.get("beneBank") + "%"));
                }
                // 联系电话
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
