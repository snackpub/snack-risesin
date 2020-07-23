package com.risesin.user.wrapper;


import com.risesin.core.jpaplus.support.BaseEntityWrapper;
import com.risesin.core.log.exception.ServiceException;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.core.tool.utils.SpringUtil;
import com.risesin.user.entity.User;
import com.risesin.user.entity.UserExt;
import com.risesin.user.service.IUserExtService;
import com.risesin.user.service.IUserService;
import com.risesin.user.vo.UserVO;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author honey
 */
public class UserWrapper extends BaseEntityWrapper<User, UserVO> {

    private static IUserService userService;
    private static IUserExtService userExtService;

    static {
        userService = SpringUtil.getBean(IUserService.class);
        userExtService = SpringUtil.getBean(IUserExtService.class);
    }

    public static UserWrapper build() {
        return new UserWrapper();
    }

    @Override
    public UserVO entityVO(User user) {
        if(Objects.isNull(user)){
            throw new ServiceException(ResultCode.PARAM_VALID_ERROR);
        }
        UserVO userVO = BeanUtil.copy(user, UserVO.class);
        if(!StringUtils.isEmpty(user.getRoleId())){
            List<String> roleName = userService.getRoleName(user.getRoleId());
            userVO.setRoleName(Func.join(roleName));
        }
        if(!StringUtils.isEmpty(user.getDeptId())){
            List<String> deptName = userService.getDeptName(user.getDeptId());
            userVO.setDeptName(Func.join(deptName));
        }
        UserExt userExt = userExtService.findById(user.getId());
        userVO.setUserExt(userExt);
        return userVO;
    }

}
