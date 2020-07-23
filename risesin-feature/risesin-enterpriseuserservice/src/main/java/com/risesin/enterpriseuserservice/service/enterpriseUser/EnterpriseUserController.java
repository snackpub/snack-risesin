package com.risesin.enterpriseuserservice.service.enterpriseUser;

import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.BeanUtil;
import com.risesin.core.tool.utils.Func;
import com.risesin.enterprise.service.EnterpriseUserService;
import com.risesin.enterprise.user.vo.EnterpriseUserVO;
import com.risesin.user.entity.EnterpriseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR Darling
 * @CREATE 2019-10-15
 * @DESCRIPTION 企业用户控制层
 * @since 1.0.0
 */

@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "企业用户管理")
@RequestMapping("/enterprise")
public class EnterpriseUserController {

    private EnterpriseUserService enterpriseUserService;

    /**
     * 根据id查询企业用户信息
     *
     * @param enterpriseUserId 企业用户id
     * @return R
     */
    @ApiOperation(value = "根据id查询企业用户信息", notes = "传入enterpriseUserId")
    @PostMapping("/user/{enterpriseUserId}")
    public R findEnterpriseById(@PathVariable @ApiParam("企业用户id") Integer enterpriseUserId) {
        EnterpriseUser enterpriseUser = enterpriseUserService.findById(enterpriseUserId);
        if (null != enterpriseUser) {
            String mobilenumber = enterpriseUser.getPhone().substring(0, 3) + "****" + enterpriseUser.getPhone().substring(7, 11);
            enterpriseUser.setPhone(mobilenumber);
            return R.data(BeanUtil.copy(enterpriseUser, EnterpriseUserVO.class));
        }
        //查询不到数据
        return R.fail("暂无数据");
    }

    /**
     * 更新企业用户资料
     *
     * @param enterpriseUser 企业用户
     * @return R
     */
    @PostMapping("/user")
    @ApiOperation(value = "更新企业用户信息", notes = "传入EnterpriseUser")
    public R updateEnterpriseUser(@RequestBody @ApiParam("enterpriseUser对象") EnterpriseUser enterpriseUser) {
        if (Func.isNotEmpty(enterpriseUser.getId())) {
            enterpriseUserService.update(enterpriseUser);
            return R.success(ResultCode.SUCCESS);
        }
        return R.fail(ResultCode.PARAM_MISS);
    }

}
