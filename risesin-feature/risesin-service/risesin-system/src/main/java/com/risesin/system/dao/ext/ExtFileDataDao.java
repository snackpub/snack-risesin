package com.risesin.system.dao.ext;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.ext.entity.ExtFileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * extFileData数据访问接口
 *
 * @author Administrator
 */
public interface ExtFileDataDao extends JpaRepository<ExtFileData, Integer>, JpaSpecificationExecutor<ExtFileData>, BaseDao<ExtFileData, Integer> {
    /**
     * 根据子方案code查询资料拓展表
     *
     * @param code
     * @return
     */
    @Query(value = "select * from t_ext_file_data t where t.fk_child_plan_code=:code and t.status=0", nativeQuery = true)
    List<ExtFileData> findAllByChildPlanCode(@Param("code") String code);
}
