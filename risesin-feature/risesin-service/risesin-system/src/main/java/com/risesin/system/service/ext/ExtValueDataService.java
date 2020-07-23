package com.risesin.system.service.ext;

import com.risesin.system.dao.ext.ExtValueDataDao;
import com.risesin.systemapi.ext.entity.ExtValueData;
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
 * extValueData服务层
 *
 * @author Administrator
 */
@Service
public class ExtValueDataService {

    @Autowired
    private ExtValueDataDao extValueDataDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ExtValueData> findAll() {
        return extValueDataDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ExtValueData> findSearch(Map whereMap, int page, int size) {
        Specification<ExtValueData> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return extValueDataDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ExtValueData> findSearch(Map whereMap) {
        Specification<ExtValueData> specification = createSpecification(whereMap);
        return extValueDataDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ExtValueData findById(Integer pkId) {
        return extValueDataDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param extValueData
     */
    public void add(ExtValueData extValueData) {
        // extValueData.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        extValueDataDao.save(extValueData);
    }

    /**
     * 修改
     *
     * @param extValueData
     */
    public void update(ExtValueData extValueData) {
        extValueDataDao.save(extValueData);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        extValueDataDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ExtValueData> createSpecification(Map searchMap) {

        return new Specification<ExtValueData>() {

            @Override
            public Predicate toPredicate(Root<ExtValueData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 输入框，单选框，复选框等等的值，全部转化为varchar类型进行存储。
                if (searchMap.get("value") != null && !"".equals(searchMap.get("value"))) {
                    predicateList.add(cb.like(root.get("value").as(String.class), "%" + (String) searchMap.get("value") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
