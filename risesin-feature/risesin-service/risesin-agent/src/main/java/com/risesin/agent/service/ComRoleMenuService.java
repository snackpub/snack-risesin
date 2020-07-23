package com.risesin.agent.service;

import com.risesin.agent.dao.ComRoleMenuDao;
import com.risesin.agent.entity.ComRoleMenu;
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
 * comRoleMenu服务层
 *
 * @author Administrator
 */
@Service
public class ComRoleMenuService {

    @Autowired
    private ComRoleMenuDao comRoleMenuDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComRoleMenu> findAll() {
        return comRoleMenuDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComRoleMenu> findSearch(Map whereMap, int page, int size) {
        Specification<ComRoleMenu> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comRoleMenuDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComRoleMenu> findSearch(Map whereMap) {
        Specification<ComRoleMenu> specification = createSpecification(whereMap);
        return comRoleMenuDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComRoleMenu findById(Integer pkId) {
        return comRoleMenuDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comRoleMenu
     */
    public void add(ComRoleMenu comRoleMenu) {
        // comRoleMenu.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comRoleMenuDao.save(comRoleMenu);
    }

    /**
     * 修改
     *
     * @param comRoleMenu
     */
    @Transactional
    public void update(ComRoleMenu comRoleMenu) {
        comRoleMenuDao.update(comRoleMenu, comRoleMenu.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comRoleMenuDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComRoleMenu> createSpecification(Map searchMap) {

        return new Specification<ComRoleMenu>() {

            @Override
            public Predicate toPredicate(Root<ComRoleMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
