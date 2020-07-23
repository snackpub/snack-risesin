package com.risesin.system.service.notice;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.system.dao.notice.SysNoticeDao;
import com.risesin.system.service.notice.imp.INoticeService;
import com.risesin.systemapi.notice.entity.SysNotice;
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
 * sysNotice服务层
 *
 * @author honey
 */
@Service
public class INoticeServiceImpl extends RisesinBaseServiceImpl<SysNoticeDao, SysNotice, Integer> implements INoticeService {

    //通知状态：发布
    private static final Byte RELEASE = 0;

    @Override
    public Page<SysNotice> findSearch(Map whereMap, Query query) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Specification<SysNotice> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query, sort);
        return baseDao.findAll(specification, pageRequest);
    }

    public List<SysNotice> findSearch(Map whereMap) {
        Specification<SysNotice> specification = createSpecification(whereMap);
        return baseDao.findAll(specification);
    }

    @Override
    public SysNotice findById(Integer id) {
        return SqlHelper.optional(baseDao.findById(id));
    }

    @Override
    public void add(SysNotice sysNotice) {
        baseDao.save(sysNotice);
    }

    @Override
    public void update(SysNotice notice) {
        baseDao.update(notice, notice.getId());
    }


    @Override
    public boolean removeBatch(List<Integer> ids) {
        return this.deleteLogic(ids, SqlConstant.UPDATE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<SysNotice> createSpecification(Map searchMap) {

        return new Specification<SysNotice>() {

            @Override
            public Predicate toPredicate(Root<SysNotice> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                // 内容
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }

                // 状态
                if (searchMap.get("status") != null) {
                    predicateList.add(cb.equal(root.get("status").as(String.class), searchMap.get("status")));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


}
