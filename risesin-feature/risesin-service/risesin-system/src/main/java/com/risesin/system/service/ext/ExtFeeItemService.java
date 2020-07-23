package com.risesin.system.service.ext;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.system.dao.ext.ExtFeeItemDao;
import com.risesin.systemapi.ext.entity.ExtFeeItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * extFeeItem服务层
 *
 * @author Administrator
 */
@Service
@AllArgsConstructor
public class ExtFeeItemService {

    private ExtFeeItemDao extFeeItemDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ExtFeeItem> findAll() {
        return extFeeItemDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ExtFeeItem> findSearch(Map whereMap, int page, int size) {
        Specification<ExtFeeItem> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return extFeeItemDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ExtFeeItem> findSearch(Map whereMap) {
        Specification<ExtFeeItem> specification = createSpecification(whereMap);
        return extFeeItemDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ExtFeeItem findById(Integer pkId) {
        return extFeeItemDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param extFeeItem
     */
    public void add(ExtFeeItem extFeeItem) {
        // extFeeItem.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        extFeeItemDao.save(extFeeItem);
    }

    /**
     * 修改
     *
     * @param extFeeItem
     */
    public void update(ExtFeeItem extFeeItem) {
        extFeeItemDao.save(extFeeItem);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        extFeeItemDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ExtFeeItem> createSpecification(Map searchMap) {

        return new Specification<ExtFeeItem>() {

            @Override
            public Predicate toPredicate(Root<ExtFeeItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
