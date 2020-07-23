package com.risesin.system.dao.system;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.system.entity.SysDept;
import com.risesin.systemapi.system.vo.DeptVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * sysDept数据访问接口
 *
 * @author Administrator
 */
@Repository
public interface SysDeptDao extends JpaRepository<SysDept, Integer>, JpaSpecificationExecutor<SysDept>, BaseDao<SysDept, Integer> {


    /**
     * 菜单树形结构
     *
     * @return
     */
    @Query(value = "select new com.risesin.systemapi.system.vo.DeptVO(d.id, d.parentId, d.deptName, d.sort, d.createTime, d.updateTime, d.description, d.status, d.createUser) from  SysDept d")
    List<DeptVO> tree();

}
