package com.risesin.system.dao.dict;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * area数据访问接口
 *
 * @author Administrator
 */
public interface AreaDao extends JpaRepository<Area, Integer>, JpaSpecificationExecutor<Area>, BaseDao<Area, Integer> {

    //@Query(value = "select area_code,name from t_area where parent_code = :parentCode",nativeQuery = true)
    List<Area> findByParentCode(String parentCode);

    Area findByCode(String code);

    List<Area> findAllByStatus(Byte status);
}
