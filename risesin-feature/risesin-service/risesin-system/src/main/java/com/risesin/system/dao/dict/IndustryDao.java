package com.risesin.system.dao.dict;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * industry数据访问接口
 *
 * @author Administrator
 */
public interface IndustryDao extends JpaRepository<Industry, Integer>, JpaSpecificationExecutor<Industry>, BaseDao<Industry, Integer> {

    List<Industry> findByParentCode(String parentCode);

    Industry findByCode(String code);

    List<Industry> findAllByStatus(Byte status);
}
