package com.risesin.agent.service;

import com.risesin.agent.dao.ComUserDao;
import com.risesin.agent.entity.ComUser;
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
import java.util.Objects;

/**
 * comUser服务层
 *
 * @author Administrator
 */
@Service
public class ComUserService {

    @Autowired
    private ComUserDao comUserDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComUser> findAll() {
        return comUserDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComUser> findSearch(Map whereMap, int page, int size) {
        if (Objects.isNull(whereMap.get("status"))) {
//            whereMap.put("status",)
        }
        Specification<ComUser> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comUserDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComUser> findSearch(Map whereMap) {
        Specification<ComUser> specification = createSpecification(whereMap);
        return comUserDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComUser findById(Integer pkId) {
        return comUserDao.findById(ComUser.class, pkId);
    }

    /**
     * 增加
     *
     * @param comUser
     */
    public void add(ComUser comUser) {
        // comUser.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comUserDao.save(comUser);
    }

    /**
     * 修改
     *
     * @param comUser
     */
    @Transactional
    public void update(ComUser comUser) {
        comUserDao.update(comUser, comUser.getId());
    }

    /**
     * 删除
     *
     * @param id
     */
    @Transactional
    public Boolean deleteById(Integer id) {
        return comUserDao.updateStatus(ComUser.class, id, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComUser> createSpecification(Map searchMap) {

        return new Specification<ComUser>() {

            @Override
            public Predicate toPredicate(Root<ComUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 用户名
                if (searchMap.get("userName") != null && !"".equals(searchMap.get("userName"))) {
                    predicateList.add(cb.like(root.get("userName").as(String.class), "%" + (String) searchMap.get("userName") + "%"));
                }
                // 手机号
                if (searchMap.get("phone") != null && !"".equals(searchMap.get("phone"))) {
                    predicateList.add(cb.like(root.get("phone").as(String.class), "%" + (String) searchMap.get("phone") + "%"));
                }
                // 来源
                if (searchMap.get("from") != null && !"".equals(searchMap.get("from"))) {
                    predicateList.add(cb.like(root.get("from").as(String.class), "%" + (String) searchMap.get("from") + "%"));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                // 如果没有状态条件,则默认增加 启用与 不启用条件
                if (Objects.isNull(searchMap.get("status"))) {
                    Predicate pred1 = cb.equal(root.get("status"), SqlConstant.NO_START_USEING);
                    Predicate pred2 = cb.equal(root.get("status"), SqlConstant.START_USEING);
                    predicateList.add(cb.or(pred1, pred2));
                } else {
                    // id
                    if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                        predicateList.add(cb.like(root.get("status").as(String.class), "%" + (Byte) searchMap.get("status") + "%"));
                    }
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public Boolean removeByIds(List<Integer> ids) {
        return comUserDao.removeBatch(ComUser.class, ids, SqlConstant.UPDATE);
    }

    public void updateStatus(Integer id, Byte status) {
        comUserDao.updateStatus(ComUser.class, id, status);
    }
}
