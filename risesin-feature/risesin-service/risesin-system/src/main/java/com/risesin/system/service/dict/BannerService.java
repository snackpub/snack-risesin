package com.risesin.system.service.dict;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.base.impl.RisesinBaseServiceImpl;
import com.risesin.system.dao.dict.AreaDao;
import com.risesin.system.dao.dict.BannerDao;
import com.risesin.systemapi.dict.entity.Area;
import com.risesin.systemapi.dict.entity.Banner;
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
 * banner服务层
 *
 * @author Administrator
 */
@Service
public class BannerService extends RisesinBaseServiceImpl<BannerDao, Banner, Integer> {

    @Autowired
    private BannerDao bannerDao;


    /**
     * 查询全部列表
     *
     * @return
     */
    public List<Banner> findAll() {
        return bannerDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Banner> findSearch(Map whereMap, int page, int size) {
        Specification<Banner> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return bannerDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<Banner> findSearch(Map whereMap) {
        Specification<Banner> specification = createSpecification(whereMap);
        return bannerDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param pkId
     * @return
     */
    @Override
    public Banner findById(Integer pkId) {
        return bannerDao.findById(pkId).get();
    }

    /**
     * 增加
     *
     * @param banner
     */
    @Override
    public void add(Banner banner) {
        // banner.setPkId( idWorker.nextId()+"" ); 雪花分布式ID生成器
        bannerDao.save(banner);
    }

    /**
     * 修改
     *
     * @param banner
     */
    public void update(Banner banner) {
        bannerDao.save(banner);
    }

    /**
     * 删除
     *
     * @param pkId
     */
    public void deleteById(Integer pkId) {
        bannerDao.deleteById(pkId);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Banner> createSpecification(Map searchMap) {

        return new Specification<Banner>() {

            @Override
            public Predicate toPredicate(Root<Banner> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 名称
                if (searchMap.get("brannerName") != null && !"".equals(searchMap.get("brannerName"))) {
                    predicateList.add(cb.like(root.get("brannerName").as(String.class), "%" + (String) searchMap.get("brannerName") + "%"));
                }
                // 位置标识
                if (searchMap.get("brannerFlag") != null && !"".equals(searchMap.get("brannerFlag"))) {
                    predicateList.add(cb.like(root.get("brannerFlag").as(String.class), "%" + (String) searchMap.get("brannerFlag") + "%"));
                }
                // 图片集合
                if (searchMap.get("imgList") != null && !"".equals(searchMap.get("imgList"))) {
                    predicateList.add(cb.like(root.get("imgList").as(String.class), "%" + (String) searchMap.get("imgList") + "%"));
                }
                // 图片路由
                if (searchMap.get("imgRouter") != null && !"".equals(searchMap.get("imgRouter"))) {
                    predicateList.add(cb.like(root.get("imgRouter").as(String.class), "%" + (String) searchMap.get("imgRouter") + "%"));
                }
                // 图片跳转全路径
                if (searchMap.get("imgHttp") != null && !"".equals(searchMap.get("imgHttp"))) {
                    predicateList.add(cb.like(root.get("imgHttp").as(String.class), "%" + (String) searchMap.get("imgHttp") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
