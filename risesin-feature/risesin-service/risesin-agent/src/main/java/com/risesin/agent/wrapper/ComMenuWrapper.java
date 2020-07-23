package com.risesin.agent.wrapper;

import com.risesin.agent.entity.ComMenu;
import com.risesin.agent.entity.vo.ComMenuVO;
import com.risesin.agent.service.ComMenuService;
import com.risesin.common.constant.CommonConstant;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.systemapi.feign.IDictClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/1
 * @DESCRIPTION 组织部门包装类
 * @since 1.0.0
 */
public class ComMenuWrapper extends BaseEntityWrapper<ComMenu, ComMenuVO> {

    private static ComMenuService comMenuService;
    private static IDictClient dictClient;

    static {
        comMenuService = SpringUtil.getBean(ComMenuService.class);
        dictClient = SpringUtil.getBean(IDictClient.class);
    }

    public static ComMenuWrapper build() {
        return new ComMenuWrapper();
    }

    @Override
    public ComMenuVO entityVO(ComMenu menu) {
        ComMenuVO comMenuVO = BeanUtil.copy(menu, ComMenuVO.class);
        if (Func.equals(menu.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            comMenuVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            ComMenu parent = comMenuService.findById(menu.getParentId());
            comMenuVO.setParentName(parent.getName());
        }
        R<String> d1 = dictClient.getValue("menu_category", Func.toInt(comMenuVO.getCategory()));
        R<String> d2 = dictClient.getValue("button_func", Func.toInt(comMenuVO.getAction()));
        R<String> d3 = dictClient.getValue("yes_no", Func.toInt(comMenuVO.getIsOpen()));
        if (d1.isSuccess()) {
            comMenuVO.setCategoryName(d1.getData());
        }
        if (d2.isSuccess()) {
            comMenuVO.setActionName(d2.getData());
        }
        if (d3.isSuccess()) {
            comMenuVO.setIsOpenName(d3.getData());
        }
        return comMenuVO;
    }

    public List<ComMenuVO> listNodeVO(List<ComMenu> list) {
        List<ComMenuVO> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }
}
