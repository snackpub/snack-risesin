package com.risesin.system.wrapper.plan;

import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.systemapi.plan.entity.ActionPlan;
import com.risesin.systemapi.plan.vo.ActionPlanVO;
import com.risesin.user.feign.IUserClient;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 * @date 2019/12/5 11:18
 **/
public class ActionPlanWrapper extends BaseEntityWrapper<ActionPlan, ActionPlanVO> {

    private static IUserClient userClient;

    static {
        userClient = SpringUtil.getBean(IUserClient.class);
    }

    public static ActionPlanWrapper build() {
        return new ActionPlanWrapper();
    }

    @Override
    public ActionPlanVO entityVO(ActionPlan entity) {
        ActionPlanVO actionPlanVO = BeanUtil.copy(entity, ActionPlanVO.class);
        R<String> d1 = userClient.getUserName(actionPlanVO.getCreateUser());
        if (d1.isSuccess()) {
            actionPlanVO.setCreateUserName(d1.getData());
        }
        return actionPlanVO;
    }

    @Override
    public List<ActionPlanVO> listVO(List<ActionPlan> list) {
        return super.listVO(list);
    }

    @Override
    public PageResult<ActionPlanVO> pageVO(Page<ActionPlan> pages) {
        return super.pageVO(pages);
    }
}
