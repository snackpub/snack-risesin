package com.risesin.system.service.dict.impl;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.dict.entity.Dict;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 字典服务
 *
 * @author honey
 * @date 2019/12/23 19:48
 */
public interface IDictService extends RisesinBaseService<Dict, Integer> {

    /**
     * 分页
     *
     * @param whereMap params
     * @param query    查询对象
     * @return ${Page}
     */
    Page<Dict> findSearch(Map whereMap, Query query);

    /**
     * 获取字典树形结构
     *
     * @return ${List}
     */
    List<Dict> tree();

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return bool
     */
    boolean removeByIds(List<Integer> ids);

    /**
     * 修改
     *
     * @param dict 字典对象
     */
    void update(Dict dict);
}
