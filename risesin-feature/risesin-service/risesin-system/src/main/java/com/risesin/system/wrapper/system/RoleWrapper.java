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
import com.risesin.core.tool.node.ForestNodeMerger;
import com.risesin.core.tool.node.INode;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.system.service.system.impl.SysRoleServiceImpl;
import com.risesin.systemapi.system.entity.SysRole;
import com.risesin.systemapi.feign.IDictClient;
import com.risesin.systemapi.system.vo.RoleVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 */
public class RoleWrapper extends BaseEntityWrapper<SysRole, RoleVO> {

    private static SysRoleServiceImpl roleService;
    private static IDictClient dictClient;

    static {
        roleService = SpringUtil.getBean(SysRoleServiceImpl.class);
        dictClient = SpringUtil.getBean(IDictClient.class);
    }

    public static RoleWrapper build() {
        return new RoleWrapper();
    }

    @Override
    public RoleVO entityVO(SysRole role) {
        RoleVO roleVO = BeanUtil.copy(role, RoleVO.class);
        if (Func.equals(role.getParentId(), CommonConstant.TOP_PARENT_ID)) {
            roleVO.setParentName(CommonConstant.TOP_PARENT_NAME);
        } else {
            SysRole parent = roleService.findById(role.getParentId());
            roleVO.setParentName(parent.getRoleName());
        }
        return roleVO;
    }

    public List<INode> listNodeVO(List<SysRole> list) {
        List<INode> collect = list.stream().map(this::entityVO).collect(Collectors.toList());
        return ForestNodeMerger.merge(collect);
    }

}
