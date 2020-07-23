package com.risesin.system.service.flowlog;

import com.risesin.system.dao.flowlog.TodotaskDao;
import com.risesin.systemapi.flowlog.entity.TodoTask;
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
 * todotask服务层
 *
 * @author Administrator
 */
@Service
public class TodotaskService {

    @Autowired
    private TodotaskDao todotaskDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<TodoTask> findAll() {
        return todotaskDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<TodoTask> findSearch(Map whereMap, int page, int size) {
        Specification<TodoTask> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return todotaskDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<TodoTask> findSearch(Map whereMap) {
        Specification<TodoTask> specification = createSpecification(whereMap);
        return todotaskDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public TodoTask findById(Integer pkId) {
        return todotaskDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param todotask
     */
    public void add(TodoTask todotask) {
        // todotask.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        todotaskDao.save(todotask);
    }

    /**
     * 修改
     *
     * @param todotask
     */
    public void update(TodoTask todotask) {
        todotaskDao.save(todotask);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        todotaskDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<TodoTask> createSpecification(Map searchMap) {

        return new Specification<TodoTask>() {

            @Override
            public Predicate toPredicate(Root<TodoTask> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
