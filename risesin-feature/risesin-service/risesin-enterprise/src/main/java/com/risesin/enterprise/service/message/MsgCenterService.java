package com.risesin.enterprise.service.message;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.enterprise.dao.message.MsgCenterDao;
import com.risesin.enterprise.message.entity.MsgCenter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * msgCenter服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class MsgCenterService extends RisesinBaseServiceImpl<MsgCenterDao, MsgCenter, Integer> {

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param query
     * @return
     */
    public Page<MsgCenter> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<MsgCenter> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    /**
     * 根据ID查询实体
     *
     * @param id)
     * @return
     */
    @Override
    public MsgCenter findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    /**
     * 增加
     *
     * @param msgCenter
     */
    @Override
    public void add(MsgCenter msgCenter) {
        baseDao.save(msgCenter);
    }


    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<MsgCenter> createSpecification(Map searchMap) {

        return new Specification<MsgCenter>() {

            @Override
            public Predicate toPredicate(Root<MsgCenter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 消息标题
                if (searchMap.get("msgTitle") != null && !"".equals(searchMap.get("msgTitle"))) {
                    predicateList.add(cb.like(root.get("msgTitle").as(String.class), "%" + (String) searchMap.get("msgTitle") + "%"));
                }
                // 消息内容
                if (searchMap.get("msgContent") != null && !"".equals(searchMap.get("msgContent"))) {
                    predicateList.add(cb.like(root.get("msgContent").as(String.class), "%" + (String) searchMap.get("msgContent") + "%"));
                }
                // 子方案编号
                if (searchMap.get("childPlanCode") != null && !"".equals(searchMap.get("childPlanCode"))) {
                    predicateList.add(cb.like(root.get("childPlanCode").as(String.class), "%" + (String) searchMap.get("childPlanCode") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 查询最新5条消息
     *
     * @param id 企业用户id
     * @return
     */
    public List get5Message(String id) {
        return baseDao.findTop5ByEntUserIdOrderByCreateTimeDesc(id);
    }

    /**
     * 查询前6条待办事项
     *
     * @param id 企业用户id
     * @return
     */
    public List get6TodoMessage(String id) {
        return baseDao.findTop6ByEntUserIdAndStatusOrderByCreateTimeDesc(id, (byte) -1);
    }

    public int getTodoMessageNum(String enterpriseUserId) {
        return baseDao.countByEntUserIdAndStatus(enterpriseUserId,(byte) -1);
    }
}
