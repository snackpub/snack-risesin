package com.risesin.agent.service;

import com.risesin.agent.dao.ComBankCardDao;
import com.risesin.agent.entity.ComBankCard;
import com.risesin.core.launch.constant.SqlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * comBankCard服务层
 *
 * @author Administrator
 */
@Service
public class ComBankCardService {

    @Autowired
    private ComBankCardDao comBankCardDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComBankCard> findAll() {
        return comBankCardDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComBankCard> findSearch(Map whereMap, int page, int size) {
        Specification<ComBankCard> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comBankCardDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComBankCard> findSearch(Map whereMap) {
        Specification<ComBankCard> specification = createSpecification(whereMap);
        return comBankCardDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComBankCard findById(Integer pkId) {
        return comBankCardDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comBankCard
     */
    public void add(ComBankCard comBankCard) {
        // comBankCard.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comBankCardDao.save(comBankCard);
    }

    /**
     * 修改
     *
     * @param comBankCard
     */
    @Transactional
    public void update(ComBankCard comBankCard) {
        comBankCardDao.update(comBankCard, comBankCard.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comBankCardDao.updateStatus(ComBankCard.class, pkId, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComBankCard> createSpecification(Map searchMap) {

        return new Specification<ComBankCard>() {

            @Override
            public Predicate toPredicate(Root<ComBankCard> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public Boolean removeByIds(List<Integer> ids) {
        return comBankCardDao.removeBatch(ComBankCard.class, ids, SqlConstant.UPDATE);
    }

    public void updateStatus(Integer id, Byte status) {
        comBankCardDao.updateStatus(ComBankCard.class, id, status);
    }
}
