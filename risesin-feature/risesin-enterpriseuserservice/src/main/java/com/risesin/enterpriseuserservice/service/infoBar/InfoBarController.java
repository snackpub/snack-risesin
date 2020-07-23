package com.risesin.enterpriseuserservice.service.infoBar;

import com.risesin.core.tool.api.R;
import com.risesin.enterprise.feign.notice.IInfoBarClient;
import com.risesin.enterprise.infoBar.vo.IncompleteCountVO;
import com.risesin.enterprise.service.message.MsgCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR Darling
 * @CREATE 2019-11-29
 * @DESCRIPTION 首页信息栏（融资预案、公告、日志）
 * @since 1.0.0
 */

@Api(tags = "首页信息栏展示")
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/infobar")
public class InfoBarController {

    private IInfoBarClient infoBarClient;

    private MsgCenterService msgCenterService;


    @ApiOperation(value = "首页统计待处理事项、未完成方案、待支付订单", notes = "")
    @GetMapping("/count")
    public R<IncompleteCountVO> getIncompleteCount() {
        //TODO userId
        String enterpriseUserId = "1";
        IncompleteCountVO data = infoBarClient.getTopBar(enterpriseUserId).getData();
        data.setTodoMessageNum(msgCenterService.getTodoMessageNum(enterpriseUserId));
        return R.data(data);
    }

    @ApiOperation(value = "首页公告、日志、预案", notes = "")
    @GetMapping("/show")
    public R getInfoBar() {
        //TODO userId
        String enterpriseUserId = "1";
        return infoBarClient.getInfoBar(enterpriseUserId);
    }


}
