package com.risesin.system.dao.dict;

import com.risesin.core.base.BaseDao;
import com.risesin.systemapi.dict.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * dict数据访问接口
 *
 * @author Administrator
 */
public interface DictDao extends JpaRepository<Dict, Integer>, JpaSpecificationExecutor<Dict>, BaseDao<Dict, Integer> {


    /**
     * 获取树形节点
     *
     * @return
     */
    @Query(value = "from Dict d where d.status = 0")
    List<Dict> tree();


    /**
     * 获取字典表对应中文
     *
     * @param code    字典编号
     * @param dictKey 字典序号
     * @return
     */
    @Query(value = " select dict_value from risesin_dict where code = :code and dict_key = :dictKey and status = 0 limit 1", nativeQuery = true)
    String getValue(String code, Integer dictKey);


    /**
     * 获取字典表
     *
     * @param code 字典编号
     * @return
     */
    @Query(value = " select id, parent_id, code, dict_key, dict_value, sort, description,status,create_time,update_time,create_user from risesin_dict where code = :code and dict_key >= 0 and status = 0", nativeQuery = true)
    List<Dict> getList(@Param("code") String code);

    /**
     * 判断当前字典键值是否已存在!
     *
     * @param code
     * @param dictKey
     * @return
     */
    Dict findByCodeAndDictKey(String code, Long dictKey);


    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update Dict a set a.status=2 where a.id in (:coll)")
    int deleteBatchIds(@Param("coll") Collection<? extends Serializable> idList);

}
