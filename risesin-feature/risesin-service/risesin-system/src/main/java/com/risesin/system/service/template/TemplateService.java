package com.risesin.system.service.template;

import com.risesin.system.dao.template.TemplateDao;
import com.risesin.systemapi.dict.entity.Template;
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
 * template服务层
 *
 * @author Administrator
 */
@Service
public class TemplateService {

    @Autowired
    private TemplateDao templateDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Template> findAll() {
        return templateDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Template> findSearch(Map whereMap, int page, int size) {
        Specification<Template> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return templateDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Template> findSearch(Map whereMap) {
        Specification<Template> specification = createSpecification(whereMap);
        return templateDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public Template findById(Integer pkId) {
        return templateDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param template
     */
    public void add(Template template) {
        // template.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        templateDao.save(template);
    }

    /**
     * 修改
     *
     * @param template
     */
    public void update(Template template) {
        templateDao.save(template);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        templateDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Template> createSpecification(Map searchMap) {

        return new Specification<Template>() {

            @Override
            public Predicate toPredicate(Root<Template> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 模板封底
                if (searchMap.get("backcoverUrl") != null && !"".equals(searchMap.get("backcoverUrl"))) {
                    predicateList.add(cb.like(root.get("backcoverUrl").as(String.class), "%" + (String) searchMap.get("backcoverUrl") + "%"));
                }
                // 模板结尾
                if (searchMap.get("end") != null && !"".equals(searchMap.get("end"))) {
                    predicateList.add(cb.like(root.get("end").as(String.class), "%" + (String) searchMap.get("end") + "%"));
                }
                // 模板开头
                if (searchMap.get("start") != null && !"".equals(searchMap.get("start"))) {
                    predicateList.add(cb.like(root.get("start").as(String.class), "%" + (String) searchMap.get("start") + "%"));
                }
                // 主体分析内容
                if (searchMap.get("mainContent") != null && !"".equals(searchMap.get("mainContent"))) {
                    predicateList.add(cb.like(root.get("mainContent").as(String.class), "%" + (String) searchMap.get("mainContent") + "%"));
                }
                // 产品内容
                if (searchMap.get("productContent") != null && !"".equals(searchMap.get("productContent"))) {
                    predicateList.add(cb.like(root.get("productContent").as(String.class), "%" + (String) searchMap.get("productContent") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
