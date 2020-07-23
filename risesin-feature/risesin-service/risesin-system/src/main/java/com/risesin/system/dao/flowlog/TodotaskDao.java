package com.risesin.system.dao.flowlog;

import com.risesin.systemapi.flowlog.entity.TodoTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * todotask数据访问接口
 *
 * @author Administrator
 */
public interface TodotaskDao extends JpaRepository<TodoTask, Integer>, JpaSpecificationExecutor<TodoTask> {

}
