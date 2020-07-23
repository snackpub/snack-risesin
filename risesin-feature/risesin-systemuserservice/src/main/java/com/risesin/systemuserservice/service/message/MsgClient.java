package com.risesin.systemuserservice.service.message;

import com.risesin.core.tool.api.R;
import com.risesin.system.service.message.MsgCenterService;
import com.risesin.systemapi.message.entity.MsgCenter;
import com.risesin.systemapi.message.feign.IMsgClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Message Feign  勿删！！！
 *
 * @author honey
 * @date 2019/12/16 11:45
 */
@ApiIgnore()
@RestController
@AllArgsConstructor
public class MsgClient implements IMsgClient {

    private MsgCenterService service;

    @Override
    @PostMapping(API_PREFIX + "/add")
    public R<String> add(@RequestBody MsgCenter msgCenter) {
        service.add(msgCenter);
        return R.success("消息保存成功!");
    }
}
