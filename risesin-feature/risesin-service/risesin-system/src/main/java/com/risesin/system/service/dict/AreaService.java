package com.risesin.system.service.dict;

import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.core.launch.constant.SqlConstant;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.system.dao.dict.AreaDao;
import com.risesin.system.dao.dict.SysBankCardDao;
import com.risesin.systemapi.dict.entity.Area;
import com.risesin.systemapi.dict.entity.SysBankCard;
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
 * area服务层
 *
 * @author Administrator
 */
@Service
public class AreaService extends RisesinBaseServiceImpl<AreaDao, Area, Integer> {

//    @Autowired
//    private AreaDao areaDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Area> findAll() {
        return baseDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Area> findSearch(Map whereMap, int page, int size) {
        Specification<Area> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return baseDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Area> findSearch(Map whereMap) {
        Specification<Area> specification = createSpecification(whereMap);
        return baseDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    @Override
    public Area findById(Integer pkId) {
        return baseDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param area
     */
    @Override
    public void add(Area area) {
        baseDao.save(area);
    }

    /**
     * 修改
     *
     * @param area
     */
    public void update(Area area) {
        baseDao.update(area, area.getId());
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        baseDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Area> createSpecification(Map searchMap) {

        return new Specification<Area>() {

            @Override
            public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                //状态
                if (searchMap.get("status") != null) {
                    predicateList.add(cb.equal(root.get("status").as(String.class), searchMap.get("status")));
                }
                // 地区编号
                if (searchMap.get("code") != null && !"".equals(searchMap.get("code"))) {
                    predicateList.add(cb.equal(root.get("code").as(String.class), (String) searchMap.get("code")));
                }
                // 地区名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 父节点
                if (searchMap.get("parentCode") != null && !"".equals(searchMap.get("parentCode"))) {
                    predicateList.add(cb.equal(root.get("parentCode").as(String.class), (String) searchMap.get("parentCode")));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

    /**
     * 根据父code查询
     *
     * @param parentCode
     * @return
     */
    public List<Area> listByPid(String parentCode) {
        return baseDao.findByParentCode(parentCode);
    }

    /**
     * 更改状态
     *
     * @param id
     * @param status
     * @return
     */
    public Boolean updateStatus(int id, Byte status) {
        return baseDao.updateStatus(Area.class, id, status);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public Boolean removeByIds(List<Integer> ids) {
        return baseDao.removeBatch(Area.class, ids, SqlConstant.UPDATE);
    }

    public Area findByCode(String code) {
        return baseDao.findByCode(code);
    }

    public List tree(Byte status) {
        List<Area> list;
        if (status == null) {
            list = baseDao.findAll();
        } else {
            list = baseDao.findAllByStatus(status);
        }
        List<IndustryVO> collect = list.stream().map(industry -> BeanUtil.copy(industry, IndustryVO.class)).collect(Collectors.toList());
        return new TreeUtils().merge(collect);
    }
}
