package com.risesin.agent.wrapper;

import com.risesin.agent.entity.ComDept;
import com.risesin.agent.entity.vo.ComDeptVO;
import com.risesin.agent.service.ComDeptService;
import com.risesin.common.constant.CommonConstant;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/1
 * @DESCRIPTION 组织部门包装类
 * @since 1.0.0
 */
public class ComDeptWrapper extends BaseEntityWrapper<ComDept, ComDeptVO> {

    private static ComDeptService comDeptService;

    static {
        comDeptService = SpringUtil.getBean(ComDeptService.class);
    }

    public static ComDeptWrapper build() {
        return new ComDeptWrapper();
    }

    @Override
    public ComDeptVO entityVO(ComDept dept) {
        ComDeptVO deptVO = BeanUtil.copy(dept, ComDeptVO.class);
        if (Func.equals(dept.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            deptVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            ComDept parent = comDeptService.findById(dept.getParentId());
            deptVO.setParentName(parent.getDeptName());
        }
        return deptVO;
    }

    public List<ComDeptVO> listNodeVO(List<ComDept> list) {
        List<ComDeptVO> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }
}
