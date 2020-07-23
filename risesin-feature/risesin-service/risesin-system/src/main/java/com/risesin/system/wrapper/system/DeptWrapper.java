package com.risesin.system.wrapper.system;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.systemapi.system.entity.SysDept;
import com.risesin.systemapi.system.vo.DeptVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类, 返回视图层所需的字段
 *
 * @author honey
 * @date 2019/12/3
 **/
public class DeptWrapper extends BaseEntityWrapper<SysDept, DeptVO> {

    public static DeptWrapper build() {
        return new DeptWrapper();
    }

    @Override
    public DeptVO entityVO(SysDept entity) {
        return BeanUtil.copy(entity, DeptVO.class);
    }


    @Override
    public List<DeptVO> listVO(List<SysDept> list) {
        List<DeptVO> collect = list.stream().map(dept -> BeanUtil.copy(dept, DeptVO.class)).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }
}
