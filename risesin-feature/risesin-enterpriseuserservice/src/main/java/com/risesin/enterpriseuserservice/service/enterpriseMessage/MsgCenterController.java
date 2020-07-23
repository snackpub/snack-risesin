package com.risesin.enterpriseuserservice.service.enterpriseMessage;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.enterprise.service.message.MsgCenterService;
import com.risesin.enterprise.message.entity.MsgCenter;
import com.risesin.enterprise.message.vo.MsgCenterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-11
 * @DESCRIPTION 消息中心
 * @since 1.0.0
 */
@RestController
@AllArgsConstructor
@Api(tags = "消息中心")
@RequestMapping("/message")
public class MsgCenterController {

    private MsgCenterService msgCenterService;


    /**
     * 分页查询（带模糊查询功能）
     *
     * @param params 查询条件封装
     * @param query  分页信息
     * @return
     */
    @ApiOperation(value = "分页查询（带模糊查询功能）", notes = "传入字段条件，页码，页大小")
    @PostMapping("/findPageSearch")
    public R<PageResult> list(@ApiParam(value = "搜索集合map") @RequestBody Map<String, Object> params,
                  @ApiParam(value = "查询对象") @RequestBody Query query) {
        //TODO 根据token获取id
        params.put("enterpriseUserId", "1");
        if (StringUtils.isEmpty((String) params.get("enterpriseUserId"))) {
            return R.fail("id不能为空!");
        }

        Page<MsgCenter> pageList = msgCenterService.findSearch(params, query);
        return R.data(new PageResult(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 首页前5条消息
     *
     * @return
     */
    @ApiOperation(value = "首页前5条消息(C端调用)", notes = "")
    @GetMapping("/get5Message")
    public R<List<MsgCenter>> get5Message() {
        //TODO 根据token获取用户id
        //return R.data(MsgCenterWrapper.build().listVO(msgCenterService.get5Message(1)));
        return R.data(msgCenterService.get5Message("1"));
    }

    /**
     * 主页展示前6条待办事项(C端调用)
     *
     * @return
     */
    @ApiOperation(value = "主页展示前6条待办事项(C端调用)", notes = "")
    @GetMapping("/get6TodoMessage")
    public R<List<MsgCenterVO>> get6TodoMessage() {
        //TODO 根据token获取用户id
        return R.data(msgCenterService.get6TodoMessage("1"));
    }


    /**
     * 添加消息
     *
     * @return
     */
    @ApiOperation(value = "添加消息", notes = "传入实体")
    @PostMapping("/add")
    public R<String> add(@RequestBody MsgCenter msgCenter) {
        msgCenterService.add(msgCenter);
        return R.data("添加成功");
    }

}
