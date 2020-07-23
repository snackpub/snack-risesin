package com.risesin.agent.entity.feign;

import com.risesin.agent.entity.feign.vo.User;
import com.risesin.agent.entity.feign.vo.UserVO;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.RemoveIds;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR Baby
 * @CREATE 2019/12/20
 * @DESCRIPTION 用户回滚类
 * @since 1.0.0
 */
@Component
public class UserServiceFeignFallback implements IUserServiceFeign {
    @Override
    public R<UserVO> detail(String id) {
        return R.fail("error");
    }

    @Override
    public R<PageResult<UserVO>> findSearch(Map searchMap, Query query) {
        return R.fail("error");
    }

    @Override
    public R<String> add(UserVO user) {
        return R.fail("error");
    }

    @Override
    public R<String> updateFeign(HashMap<String,String> hashMap) {
        return R.fail("error");
    }

    @Override
    public R<Boolean> remove(RemoveIds ids) {
        return R.fail("error");
    }

    @Override
    public R<Boolean> grant(UserVO user) {
        return R.fail("error");
    }

    @Override
    public R<String> updateStatusById(User user) {
        return R.fail("error");
    }
}
