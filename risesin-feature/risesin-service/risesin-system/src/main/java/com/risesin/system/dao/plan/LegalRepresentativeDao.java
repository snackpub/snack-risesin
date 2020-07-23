package com.risesin.system.dao.plan;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.plan.entity.LegalRepresentative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * legalRepresentative数据访问接口
 *
 * @author Administrator
 */
public interface LegalRepresentativeDao extends JpaRepository<LegalRepresentative, Integer>, JpaSpecificationExecutor<LegalRepresentative>, BaseDao<LegalRepresentative, Integer> {

}
