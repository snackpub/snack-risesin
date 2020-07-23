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
package com.risesin.system.service.plan;

import com.risesin.core.base.RisesinBaseService;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.systemapi.plan.bo.AdditionalCustomerMaterialsBO;
import com.risesin.systemapi.plan.entity.ActionPlan;
import com.risesin.systemapi.plan.vo.ActionPlanCustomInfoVO;
import com.risesin.systemapi.plan.vo.IncompletePlanVO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author honey
 * @date 2019/12/25 16:24
 */
public interface IActionPlanService extends RisesinBaseService<ActionPlan, Integer> {

    /**
     * 条件查询+分页
     *
     * @param whereMap 参数
     * @param query    分页参数
     * @return Page<ActionPlan>
     */
    Page<ActionPlan> findSearch(Map whereMap, Query query);

    /**
     * 根据方案code查询详情
     *
     * @param planCode 方案code
     * @return ActionPlan
     */
    ActionPlan findById(String planCode);

    /**
     * 融资预案详情查询
     *
     * @param financingCode 融资预案Code
     * @return customerMaterialsBO业务对象
     */
    AdditionalCustomerMaterialsBO additionalCustomerMaterials(String financingCode);

    /**
     * 保存客户完善的资料
     *
     * @param customerMaterialsBO 业务接收对象
     * @return boolean
     */
    boolean saveAdditionalCustomerMaterials(AdditionalCustomerMaterialsBO customerMaterialsBO);

    /**
     * 修改执行方案
     *
     * @param actionPlanCustomInfoVO vo
     * @return bool
     */
    boolean updateAdditionalCustomerMaterials(ActionPlanCustomInfoVO actionPlanCustomInfoVO);

    /**
     * 查询已保存的执行方案
     *
     * @param planCode 执行方案Code
     */
    ActionPlanCustomInfoVO selectActionPlanInfo(String planCode);

    /**
     * 查询未完成的父方案数
     *
     * @param enterpriseUserId 企业用户ID
     * @return int
     */
    int getTopBar(String enterpriseUserId);

    /**
     * C端主页展示6条未完成的融资方案(C端调用)
     *
     * @param enterpriseUserId 企业用户ID
     * @return List<IncompletePlanVO>
     */
    List<IncompletePlanVO> get6IncompleteActionPlanInfo(String enterpriseUserId);

    /**
     * 未完成的父执行方案列表(C端调用)
     *
     * @param enterpriseUserId 企业用户ID
     * @return List<ActionPlan>
     */
    List<ActionPlan> getIncompleteActionPlanInfo(String enterpriseUserId);
}
