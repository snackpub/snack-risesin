package com.risesin.system.service.plan;

import com.risesin.system.dao.plan.InfoCheckResultDao;
import com.risesin.systemapi.plan.entity.InfoCheckResult;
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
 * infoCheckResult服务层
 *
 * @author Administrator
 */
@Service
public class InfoCheckResultService {

    @Autowired
    private InfoCheckResultDao infoCheckResultDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<InfoCheckResult> findAll() {
        return infoCheckResultDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<InfoCheckResult> findSearch(Map whereMap, int page, int size) {
        Specification<InfoCheckResult> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return infoCheckResultDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<InfoCheckResult> findSearch(Map whereMap) {
        Specification<InfoCheckResult> specification = createSpecification(whereMap);
        return infoCheckResultDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param PkId
     * @return
     */
    public InfoCheckResult findById(Integer PkId) {
        return infoCheckResultDao.findById(PkId).get();
    }

    /**
     * 增加
     *
     * @param infoCheckResult
     */
    public void add(InfoCheckResult infoCheckResult) {
        // infoCheckResult.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        infoCheckResultDao.save(infoCheckResult);
    }

    /**
     * 修改
     *
     * @param infoCheckResult
     */
    public void update(InfoCheckResult infoCheckResult) {
        infoCheckResultDao.save(infoCheckResult);
    }

    /**
     * 删除
     *
     * @param PkId
     */
    public void deleteById(Integer PkId) {
        infoCheckResultDao.deleteById(PkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<InfoCheckResult> createSpecification(Map searchMap) {

        return new Specification<InfoCheckResult>() {

            @Override
            public Predicate toPredicate(Root<InfoCheckResult> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
