package com.risesin.system.dao.ext;

import com.risesin.systemapi.ext.entity.ExtValueData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * extValueData数据访问接口
 *
 * @author Administrator
 */
public interface ExtValueDataDao extends JpaRepository<ExtValueData, Integer>, JpaSpecificationExecutor<ExtValueData> {

}
