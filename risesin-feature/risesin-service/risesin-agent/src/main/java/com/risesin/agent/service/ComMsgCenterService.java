package com.risesin.agent.service;

import com.risesin.agent.dao.ComMsgCenterDao;
import com.risesin.agent.entity.ComMsgCenter;
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
 * comMsgCenter服务层
 *
 * @author Administrator
 */
@Service
public class ComMsgCenterService {

    @Autowired
    private ComMsgCenterDao comMsgCenterDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComMsgCenter> findAll() {
        return comMsgCenterDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComMsgCenter> findSearch(Map whereMap, int page, int size) {
        Specification<ComMsgCenter> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comMsgCenterDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComMsgCenter> findSearch(Map whereMap) {
        Specification<ComMsgCenter> specification = createSpecification(whereMap);
        return comMsgCenterDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComMsgCenter findById(Integer pkId) {
        return comMsgCenterDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comMsgCenter
     */
    public void add(ComMsgCenter comMsgCenter) {
        // comMsgCenter.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comMsgCenterDao.save(comMsgCenter);
    }

    /**
     * 修改
     *
     * @param comMsgCenter
     */
    @Transactional
    public void update(ComMsgCenter comMsgCenter) {
//		comMsgCenterDao.save(comMsgCenter);
        comMsgCenterDao.update(comMsgCenter, comMsgCenter.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comMsgCenterDao.updateStatus(ComMsgCenter.class, pkId, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComMsgCenter> createSpecification(Map searchMap) {

        return new Specification<ComMsgCenter>() {

            @Override
            public Predicate toPredicate(Root<ComMsgCenter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 消息标题
                if (searchMap.get("msgTitle") != null && !"".equals(searchMap.get("msgTitle"))) {
                    predicateList.add(cb.like(root.get("msgTitle").as(String.class), "%" + (String) searchMap.get("msgTitle") + "%"));
                }
                // 消息内容
                if (searchMap.get("msgContent") != null && !"".equals(searchMap.get("msgContent"))) {
                    predicateList.add(cb.like(root.get("msgContent").as(String.class), "%" + (String) searchMap.get("msgContent") + "%"));
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
        return comMsgCenterDao.removeBatch(ComMsgCenter.class, ids, SqlConstant.UPDATE);
    }

    public void updateStatus(Integer id, Byte status) {
        comMsgCenterDao.updateStatus(ComMsgCenter.class, id, status);
    }
}
