package com.risesin.system.service.dict;

import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.system.dao.dict.IndustryDao;
import com.risesin.systemapi.dict.entity.Industry;
import com.risesin.systemapi.dict.vo.IndustryVO;
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
import java.util.stream.Collectors;

/**
 * industry服务层
 *
 * @author Administrator
 */
@Service
public class IndustryService {

    @Autowired
    private IndustryDao industryDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Industry> findAll() {
        return industryDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Industry> findSearch(Map whereMap, int page, int size) {
        Specification<Industry> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return industryDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Industry> findSearch(Map whereMap) {
        Specification<Industry> specification = createSpecification(whereMap);
        return industryDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    public Industry findById(Integer pkId) {
        return industryDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param industry
     */
    public void add(Industry industry) {
        industryDao.save(industry);
    }

    /**
     * 修改
     *
     * @param industry
     */
    public void update(Industry industry) {
        industryDao.update(industry, industry.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        industryDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Industry> createSpecification(Map searchMap) {

        return new Specification<Industry>() {

            @Override
            public Predicate toPredicate(Root<Industry> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 行业码
                if (searchMap.get("code") != null && !"".equals(searchMap.get("code"))) {
                    predicateList.add(cb.equal(root.get("code").as(String.class), (String) searchMap.get("code")));
                }
                // 行业名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }

                // 父主键
                if (searchMap.get("parentCode") != null && !"".equals(searchMap.get("parentCode"))) {
                    predicateList.add(cb.equal(root.get("parentCode").as(String.class), (String) searchMap.get("parentCode")));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 更改状态
     *
     * @param id
     * @param status
     * @return
     */
    public Boolean updateStatus(int id, Byte status) {
        return industryDao.updateStatus(Industry.class, id, status);
    }

    /**
     * 根据父id查询
     *
     * @param parentCode
     * @return
     */
    public List<Industry> listByPid(String parentCode) {
        return industryDao.findByParentCode(parentCode);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public Boolean removeByIds(List<Integer> ids) {
        return industryDao.removeBatch(Industry.class, ids, SqlConstant.UPDATE);
    }

    public List tree(Byte status) {
        List<Industry> list;
        if (status == null) {
            list = industryDao.findAll();
        } else {
            list = industryDao.findAllByStatus(status);
        }
        //List<Industry> list = industryDao.findAllForHasStatus(Industry.class,true);
        List<IndustryVO> collect = list.stream().map(industry -> BeanUtil.copy(industry, IndustryVO.class)).collect(Collectors.toList());
        return new TreeUtils().merge(collect);
    }

    public Industry findByCode(String code) {
        return industryDao.findByCode(code);
    }
}
