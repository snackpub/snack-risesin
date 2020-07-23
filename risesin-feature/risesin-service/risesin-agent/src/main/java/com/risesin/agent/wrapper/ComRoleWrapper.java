package com.risesin.agent.wrapper;

import com.risesin.agent.entity.ComRole;
import com.risesin.agent.entity.vo.ComRoleVO;
import com.risesin.agent.service.ComRoleService;
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
public class ComRoleWrapper extends BaseEntityWrapper<ComRole, ComRoleVO> {

    private static ComRoleService comRoleService;

    static {
        comRoleService = SpringUtil.getBean(ComRoleService.class);
    }

    public static ComRoleWrapper build() {
        return new ComRoleWrapper();
    }

    @Override
    public ComRoleVO entityVO(ComRole role) {
        ComRoleVO roleVO = BeanUtil.copy(role, ComRoleVO.class);
        if (Func.equals(role.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            roleVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            ComRole parent = comRoleService.findById(role.getParentId());
            roleVO.setParentName(parent.getRoleName());
        }
        return roleVO;
    }

    public List<ComRoleVO> listNodeVO(List<ComRole> list) {
        List<ComRoleVO> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }
}
