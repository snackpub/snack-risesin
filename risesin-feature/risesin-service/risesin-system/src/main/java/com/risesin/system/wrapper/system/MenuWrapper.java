/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.system.wrapper.system;

import com.risesin.common.constant.CommonConstant;
import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.system.SysMenuService;
import com.risesin.systemapi.system.entity.SysMenu;
import com.risesin.systemapi.feign.IDictClient;
import com.risesin.systemapi.system.vo.MenuVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 */
public class MenuWrapper extends BaseEntityWrapper<SysMenu, MenuVO> {

    private static SysMenuService menuService;

    private static IDictClient dictClient;

    static {
        menuService = SpringUtil.getBean(SysMenuService.class);
        dictClient = SpringUtil.getBean(IDictClient.class);
    }

    public static MenuWrapper build() {
        return new MenuWrapper();
    }

    @Override
    public MenuVO entityVO(SysMenu menu) {
        MenuVO menuVO = BeanUtil.copy(menu, MenuVO.class);
        if (Func.equals(menu.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            menuVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            SysMenu parent = menuService.findById(menu.getParentId());
            menuVO.setParentName(parent.getName());
        }
        R<String> d1 = dictClient.getValue("menu_category", Func.toInt(menuVO.getCategory()));
        R<String> d3 = dictClient.getValue("yes_no", Func.toInt(menuVO.getIsOpen()));
        if (d1.isSuccess()) {
            menuVO.setCategoryName(d1.getData());
        }
        if (d3.isSuccess()) {
            menuVO.setIsOpenName(d3.getData());
        }
        return menuVO;
    }


    public List<MenuVO> listNodeVO(List<SysMenu> list) {
        List<MenuVO> collect = list.stream().map(menu -> BeanUtil.copy(menu, MenuVO.class)).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }

}
