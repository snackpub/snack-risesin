package com.risesin.systemuserservice.service.infoBar;

import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.tool.api.R;
import com.risesin.system.service.flowlog.UserLogService;
import com.risesin.system.service.notice.imp.INoticeService;
import com.risesin.system.service.order.ISysOrderService;
import com.risesin.system.service.plan.EnterpriseInfoService;
import com.risesin.system.service.plan.IActionPlanService;
import com.risesin.systemapi.flowlog.entity.UserLog;
import com.risesin.systemapi.infoBar.vo.InfoBar;
import com.risesin.systemapi.notice.entity.SysNotice;
import com.risesin.systemapi.order.vo.IncompleteCountVO;
import com.risesin.systemapi.plan.interfaceresult.FinancingBarInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private EnterpriseInfoService enterpriseInfoService;

    private IActionPlanService actionPlanService;

    private ISysOrderService sysOrderService;

    private INoticeService noticeService;

    private UserLogService userLogService;

    /**
     * 公告、日志、预案
     *
     * @param enterpriseUserId
     * @return
     */
    @ApiOperation(value = "首页公告、日志、预案(C端调用)", notes = "传入enterpriseUserId")
    @GetMapping("/show/{enterpriseUserId}")
    @ApiOperationSupport(order = 1)
    public R getInfoBar(@PathVariable String enterpriseUserId) {
        List<FinancingBarInfo> financingPlanInfo = enterpriseInfoService.find5FinancingPlanInfo(enterpriseUserId);
        Query query = new Query();
        query.setPageNo(1);
        query.setPageSize(5);
        Map params = new HashMap<>();
        params.put("operationUser", "1");
        List<UserLog> userLogs = userLogService.findSearch(params, query).getContent();
        //状态:已发布
        params.put("status", new Byte("0"));
        List<SysNotice> notices = noticeService.findSearch(params, query).getContent();

        InfoBar infoBar = new InfoBar();
        infoBar.setFinancingPlanInfo(financingPlanInfo);
        infoBar.setUserLogs(userLogs);
        infoBar.setNotices(notices);
        return R.data(infoBar);
    }

    @ApiOperation(value = "首页统计方案、订单、支付(C端调用)", notes = "传入enterpriseUserId")
    @GetMapping("/count/{enterpriseUserId}")
    @ApiOperationSupport(order = 2)
    public R getTopBar(@PathVariable String enterpriseUserId) {
        //查询未完成的方案数
        int incompletePlanNum = actionPlanService.getTopBar(enterpriseUserId);
        //待支付订单数
        int unpaidNum = sysOrderService.getUnpaidOrderNum(enterpriseUserId);

        IncompleteCountVO incompleteCountVO = new IncompleteCountVO();
        incompleteCountVO.setIncompletePlanNum(incompletePlanNum);
        incompleteCountVO.setUnpaidNum(unpaidNum);

        return R.data(incompleteCountVO);
    }
}
