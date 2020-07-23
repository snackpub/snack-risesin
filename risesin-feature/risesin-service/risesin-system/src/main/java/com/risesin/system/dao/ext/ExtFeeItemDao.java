package com.risesin.system.dao.ext;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.ext.entity.ExtFeeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * extFeeItem数据访问接口
 *
 * @author Administrator
 */
public interface ExtFeeItemDao extends JpaRepository<ExtFeeItem, Integer>, JpaSpecificationExecutor<ExtFeeItem>, BaseDao<ExtFeeItem, Integer> {


    /**
     * 查询子方案所有收费项
     */


}
