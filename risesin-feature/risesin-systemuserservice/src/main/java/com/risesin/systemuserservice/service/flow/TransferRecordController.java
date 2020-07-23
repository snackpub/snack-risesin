package com.risesin.systemuserservice.service.flow;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记录方案或子方案整体执行,各个经手人流转过程
 *
 * @author honey
 * @date 2019/12/4 15:28
 **/
@RestController
@AllArgsConstructor
@Api(tags = "方案或子方案流程管理")
@RequestMapping("/planFlow")
public class TransferRecordController {
}
