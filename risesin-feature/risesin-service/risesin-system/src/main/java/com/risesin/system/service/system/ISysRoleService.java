/*
 *  Copyright (c) 2020-2022, Qiuhaijun (3524422399@qq.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.risesin.system.service.system;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.system.entity.SysRole;
import com.risesin.systemapi.system.vo.RoleVO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * 系统角色接口
 *
 * @author honey
 * @date 2019/12/24 20:09
 */
public interface ISysRoleService extends RisesinBaseService<SysRole, Integer> {

    /**
     * 分页列表
     *
     * @param whereMap 参数
     * @param query    查询对象
     * @return Page<SysRole>
     */
    Page<SysRole> findSearch(Map whereMap, Query query);

    /**
     * 角色树
     *
     * @return List<RoleVO>
     */
    List<RoleVO> tree();

    /**
     * 角色授权菜单
     *
     * @param roleIds 角色id集合
     * @param menuIds 菜单id集合
     * @return boolean
     */
    boolean grant(List<Integer> roleIds, List<Integer> menuIds);

    /**
     * 角色授权权限
     *
     * @param roleIds       角色id集合
     * @param permissionIds 权限id集合
     * @return boolean
     */
    boolean authority(List<Integer> roleIds, List<Integer> permissionIds);

}
