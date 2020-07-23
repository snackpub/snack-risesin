package com.risesin.systemuserservice.service.message;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.system.service.message.MsgCenterService;
import com.risesin.system.wrapper.msgconter.MsgCenterWrapper;
import com.risesin.systemapi.message.entity.MsgCenter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 勿删！！！
 * @author Administrator
 */
@RestController
@AllArgsConstructor
@Api(tags = "消息中心")
@RequestMapping("/message")
public class MsgCenterController {

    private MsgCenterService msgCenterService;


    /**
     * 分页查询
     *
     * @param searchMap 查询条件封装
     * @param query     分页信息
     * @return
     */
    @ApiOperation(value = "分页查询（带模糊查询功能）", notes = "传入字段条件，页码，页大小")
    @PostMapping("/findPageSearch")
    public R<PageResult> list(@ApiParam(value = "搜索集合map") @RequestBody Map<String, Object> searchMap,
                              @ApiParam(value = "查询对象") @ApiIgnore @RequestBody Query query) {
        Page<MsgCenter> pages = msgCenterService.findSearch(searchMap, query);
        return R.data(MsgCenterWrapper.build().pageVO(pages));
    }


}
