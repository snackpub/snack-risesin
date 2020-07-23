package com.risesin.system.service.ext;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.system.dao.ext.ExtFileDataDao;
import com.risesin.systemapi.ext.entity.ExtFileData;
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
 * extFileData服务层
 *
 * @author Administrator
 */
@Service
public class ExtFileDataService {

    @Autowired
    private ExtFileDataDao extFileDataDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ExtFileData> findAll() {
        return extFileDataDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ExtFileData> findSearch(Map whereMap, int page, int size) {
        Specification<ExtFileData> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return extFileDataDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ExtFileData> findSearch(Map whereMap) {
        Specification<ExtFileData> specification = createSpecification(whereMap);
        return extFileDataDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ExtFileData findById(Integer pkId) {
        return extFileDataDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param extFileData
     */
    public void add(ExtFileData extFileData) {
        // extFileData.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        extFileDataDao.save(extFileData);
    }

    /**
     * 修改
     *
     * @param extFileData
     */
    public void update(ExtFileData extFileData) {
        extFileDataDao.save(extFileData);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        extFileDataDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ExtFileData> createSpecification(Map searchMap) {

        return new Specification<ExtFileData>() {

            @Override
            public Predicate toPredicate(Root<ExtFileData> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 文件名
                if (searchMap.get("fileName") != null && !"".equals(searchMap.get("fileName"))) {
                    predicateList.add(cb.like(root.get("fileName").as(String.class), "%" + (String) searchMap.get("fileName") + "%"));
                }
                // 文件路径
                if (searchMap.get("path") != null && !"".equals(searchMap.get("path"))) {
                    predicateList.add(cb.like(root.get("path").as(String.class), "%" + (String) searchMap.get("path") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
