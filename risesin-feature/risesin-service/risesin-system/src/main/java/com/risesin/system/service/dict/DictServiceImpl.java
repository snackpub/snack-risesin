package com.risesin.system.service.dict;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SqlHelper;
import com.risesin.core.tool.utils.StringPool;
import com.risesin.system.dao.dict.DictDao;
import com.risesin.system.service.dict.impl.IDictService;
import com.risesin.systemapi.dict.entity.Dict;
import org.springframework.cache.annotation.Cacheable;
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

import static com.risesin.common.cache.CacheNames.DICT_LIST;
import static com.risesin.common.cache.CacheNames.DICT_VALUE;

/**
 * dict服务层
 *
 * @author honey
 * @date 2019/12/23
 */
@Service
public class DictServiceImpl extends RisesinBaseServiceImpl<DictDao, Dict, Integer> implements IDictService {

    @Override
    public Page<Dict> findSearch(Map whereMap, Query query) {
        Specification<Dict> specification = createSpecification(whereMap);
        PageRequest pageRequest = QueryUtil.getPage(query);
        return baseDao.findAll(specification, pageRequest);
    }

    @Override
    public List<Dict> tree() {
        return ForestNodeMerger.merge(baseDao.tree());
    }

    /**
     * 获取字典
     *
     * @param code 字典码
     * @return List<Dict>
     */
    @Cacheable(cacheNames = DICT_LIST, key = "#code")
    public List<Dict> getList(String code) {
        return baseDao.getList(code);
    }

    /**
     * 获取字典名称
     *
     * @param code    字典码
     * @param dictKey 字典值
     * @return str
     */
    @Cacheable(cacheNames = DICT_VALUE, key = "#code+'_'+#dictKey")
    public String getValue(String code, Integer dictKey) {
        return Func.toStr(baseDao.getValue(code, dictKey), StringPool.EMPTY);
    }

    @Override
    public boolean removeByIds(List<Integer> ids) {
        return SqlHelper.retBool(baseDao.deleteBatchIds(ids));
    }


    @Override
    @Cacheable(cacheNames = DICT_VALUE, key = "#id")
    public Dict findById(Integer id) {
        return baseDao.findById(Dict.class, id);
    }

    @Override
    public void add(Dict dict) {
        assert dict != null;
        if (dict.getId() != null) {
            this.update(dict);
        }
        baseDao.save(dict);
    }

    @Override
    public void update(Dict dict) {
        baseDao.update(dict, dict.getId());
    }


    @Override
    public boolean updateStatus(Integer id, Byte status) {
        return baseDao.updateStatus(Dict.class, id, status);
    }


    /**
     * 动态条件构建
     *
     * @param searchMap 参数
     * @return Specification<Dict>
     */
    private Specification<Dict> createSpecification(Map searchMap) {

        return new Specification<Dict>() {

            @Override
            public Predicate toPredicate(Root<Dict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 字典码
                if (searchMap.get("code") != null && !"".equals(searchMap.get("code"))) {
                    predicateList.add(cb.like(root.get("code").as(String.class), "%" + (String) searchMap.get("code") + "%"));
                }
                // 字典名称
                if (searchMap.get("dictValue") != null && !"".equals(searchMap.get("dictValue"))) {
                    predicateList.add(cb.like(root.get("dictValue").as(String.class), "%" + (String) searchMap.get("dictValue") + "%"));
                }
                // 状态
                if (searchMap.get("status") != null && !"".equals(searchMap.get("status"))) {
                    predicateList.add(cb.like(root.get("status").as(String.class), "%" + (String) searchMap.get("status") + "%"));
                }
                // 创建人
                if (searchMap.get("createBy") != null && !"".equals(searchMap.get("createBy"))) {
                    predicateList.add(cb.like(root.get("createUser").as(String.class), "%" + (String) searchMap.get("createBy") + "%"));
                }
                // 创建时间区间查询
                if (searchMap.get("startDate") != null && !"".equals(searchMap.get("startDate"))) {
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), (String) searchMap.get("startDate")));
                }
                if (searchMap.get("endDate") != null && !"".equals(searchMap.get("endDate"))) {
                    predicateList.add(cb.lessThanOrEqualTo((root.get("createTime").as(String.class)), (String) searchMap.get("endDate")));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "" + Func.toInt(searchMap.get("id"))));
                }
                // 父主键
                if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
                    predicateList.add(cb.like(root.get("parentId").as(String.class), "" + Func.toInt(searchMap.get("parentId"))));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
