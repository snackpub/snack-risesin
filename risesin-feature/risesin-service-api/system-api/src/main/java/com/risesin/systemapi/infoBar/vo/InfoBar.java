package com.risesin.systemapi.infoBar.vo;

import com.risesin.systemapi.flowlog.entity.UserLog;
import com.risesin.systemapi.notice.entity.SysNotice;
import com.risesin.systemapi.plan.interfaceresult.FinancingBarInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-25
 * @DESCRIPTION
 * @since 1.0.0
 */
@Data
@ApiModel("首页信息")
public class InfoBar {

    @ApiModelProperty("融资预案")
    private List<FinancingBarInfo> financingPlanInfo;

    @ApiModelProperty("日志")
    private List<UserLog> userLogs;

    @ApiModelProperty("公告")
    private List<SysNotice> notices;
}
