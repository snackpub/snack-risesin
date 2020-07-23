package com.risesin.enterprise.message.vo;

import com.risesin.enterprise.message.entity.MsgCenter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @AUTHOR Darling
 * @CREATE 2019-12-16
 * @DESCRIPTION 消息VO
 * @since 1.0.0
 */
@Data
@ApiModel("消息VO")
public class MsgCenterVO extends MsgCenter {

    @ApiModelProperty("跳转链接")
    private String path;
}
