package com.risesin.agent.service;

import com.risesin.agent.dao.ComMenuDao;
import com.risesin.agent.entity.ComMenu;
import com.risesin.agent.entity.vo.ComMenuVO;
import com.risesin.agent.wrapper.ComMenuWrapper;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.node.ForestNodeMerger;
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
 * comMenu服务层
 *
 * @author Administrator
 */
@Service
public class ComMenuService {

    @Autowired
    private ComMenuDao comMenuDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<ComMenu> findAll() {
        return comMenuDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<ComMenu> findSearch(Map whereMap, int page, int size) {
        Specification<ComMenu> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return comMenuDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<ComMenu> findSearch(Map whereMap) {
        Specification<ComMenu> specification = createSpecification(whereMap);
        return comMenuDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public ComMenu findById(Integer pkId) {
        return comMenuDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param comMenu
     */
    public void add(ComMenu comMenu) {
        // comMenu.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        comMenuDao.save(comMenu);
    }

    /**
     * 修改
     *
     * @param comMenu
     */
    @Transactional
    public void update(ComMenu comMenu) {
        comMenuDao.update(comMenu, comMenu.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    @Transactional
    public void deleteById(Integer pkId) {
        comMenuDao.updateStatus(ComMenu.class, pkId, SqlConstant.LOGOUT_OR_DELETE);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<ComMenu> createSpecification(Map searchMap) {

        return new Specification<ComMenu>() {

            @Override
            public Predicate toPredicate(Root<ComMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 菜单别名
                if (searchMap.get("alias") != null && !"".equals(searchMap.get("alias"))) {
                    predicateList.add(cb.like(root.get("alias").as(String.class), "%" + (String) searchMap.get("alias") + "%"));
                }
                // 菜单名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 菜单路径
                if (searchMap.get("path") != null && !"".equals(searchMap.get("path"))) {
                    predicateList.add(cb.like(root.get("path").as(String.class), "%" + (String) searchMap.get("path") + "%"));
                }
                // 菜单资源Icon
                if (searchMap.get("source") != null && !"".equals(searchMap.get("source"))) {
                    predicateList.add(cb.like(root.get("source").as(String.class), "%" + (String) searchMap.get("source") + "%"));
                }
                // 备注
                if (searchMap.get("remark") != null && !"".equals(searchMap.get("remark"))) {
                    predicateList.add(cb.like(root.get("remark").as(String.class), "%" + (String) searchMap.get("remark") + "%"));
                }
                // id
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (Integer) searchMap.get("id") + "%"));
                }
                // 父主键
                if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
                    predicateList.add(cb.like(root.get("parentId").as(String.class), "%" + (Integer) searchMap.get("parentId") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    public void updateStatus(Integer id, Byte status) {
        comMenuDao.updateStatus(ComMenu.class, id, status);
    }

    public List<ComMenuVO> tree() {
        List<ComMenu> comList = comMenuDao.findAllForHasStatus(ComMenu.class, true);
        return ForestNodeMerger.merge(ComMenuWrapper.build().listNodeVO(comList));
    }
}
