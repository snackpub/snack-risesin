package com.risesin.systemuserservice.service.plan;

import com.risesin.core.boot.ctrl.RisesinController;
import com.risesin.core.jpaplus.support.Query;
import com.risesin.core.jpaplus.support.QueryUtil;
import com.risesin.core.log.annotation.ApiLog;
import com.risesin.core.tool.api.PageResult;
import com.risesin.core.tool.api.R;
import com.risesin.core.tool.api.ResultCode;
import com.risesin.core.tool.utils.Func;
import com.risesin.system.service.flowlog.TransferRecordService;
import com.risesin.system.service.plan.ActionChildPlanService;
import com.risesin.system.service.plan.IActionPlanService;
import com.risesin.system.wrapper.plan.ActionPlanWrapper;
import com.risesin.systemapi.plan.bo.AdditionalCustomerMaterialsBO;
import com.risesin.systemapi.plan.entity.ActionChildPlan;
import com.risesin.systemapi.plan.entity.ActionPlan;
import com.risesin.systemapi.plan.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 执行方案管理
 *
 * @author : honey
 * @date : 2019-11-27 18:44
 **/
@RestController
@AllArgsConstructor
@Api(tags = "执行方案管理")
@RequestMapping("/actionPlan")
public class ActionPlanController extends RisesinController {

    private IActionPlanService actionPlanService;

    private TransferRecordService transferRecordService;

    private ActionChildPlanService actionChildPlanService;

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入actionPlanId")
    public R<ActionPlan> detail(@RequestParam @ApiParam("主键ID") @NotBlank(message = "id不能为空") String planId) {
        ActionPlan detail = actionPlanService.findById(Func.toInt(planId));
        return R.data(ActionPlanWrapper.build().entityVO(detail));
    }


    /**
     * 列表
     */
    @PostMapping("/list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "列表", notes = "传入planName/planCode")
    public R<PageResult<ActionPlanVO>> list(@ApiIgnore @RequestBody Map<String, Object> params, @ApiIgnore @RequestBody Query query) {
        Page<ActionPlan> pages = actionPlanService.findSearch(params, query);
        return R.data(ActionPlanWrapper.build().pageVO(pages));
    }

    @GetMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(actionPlanService.deleteLogic(Func.toIntList(ids)));
    }

    /**
     * 主页展示6条未完成的融资方案(C端调用)
     */
    @ApiOperation(value = "主页展示6条未完成的融资方案(C端调用)", notes = "传入enterpriseUserId")
    @GetMapping("/incompletePlan/{enterpriseUserId}")
    public R<List<IncompletePlanVO>> get6IncompleteActionPlanInfo(@PathVariable String enterpriseUserId) {
        return R.data(actionPlanService.get6IncompleteActionPlanInfo(enterpriseUserId));
    }


    /**
     * 未完成的父执行方案列表(C端调用)
     */
    @ApiOperation(value = "未完成的父执行方案列表(C端调用)", notes = "传入enterpriseUserId")
    @GetMapping("/incompletePlanList/{enterpriseUserId}")
    public R<List<ActionPlan>> getIncompleteActionPlanInfo(@PathVariable String enterpriseUserId) {
        return R.data(actionPlanService.getIncompleteActionPlanInfo(enterpriseUserId));
    }

    /**
     * 查询我的融资方案(C端调用)
     *
     * @param params 查询条件:flow(流程状态),companyName,actionPlanCode,enterpriseUserId(C端必填)
     * @param query  分页信息
     * @returns R
     */
    @ApiOperation(value = "“我的融资方案”(C端调用)", notes = "传入token")
    @PostMapping("/myplans")
    public R myActionPlan(@RequestBody Map<String, Object> params, @RequestBody Query query) {
        if (params == null) {
            return R.fail("参数有误");
        }

        Page<ActionPlan> page = actionPlanService.findSearch(params, query);

        return R.data(ActionPlanWrapper.build().pageVO(page));
    }

    /**
     * 根据融资预案code查询客户资料详情
     *
     * @param financingCode 融资预案code
     * @return AdditionalCustomerMaterialsVO
     */
    @ApiLog("根据融资预案code查询客户资料详情")
    @GetMapping("/finPlanDetail")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "根据融资预案code获取客户资料详情", notes = "传入financingCode")
    public R<AdditionalCustomerMaterialsVO> additionalCustomerMaterials(@ApiParam(value = "融资预案code", required = true) @RequestParam String financingCode) {
        AdditionalCustomerMaterialsVO materialsVO = actionPlanService.additionalCustomerMaterials(financingCode);
        return R.data(materialsVO);
    }

    /**
     * 父方案查询
     *
     * @param actionCode 执行方案code
     * @return AdditionalCustomerMaterialsVO
     */
    @ApiLog("父方案查询")
    @GetMapping("/actionPlanDetail")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "父方案查询", notes = "传入actionCode")
    public R<ActionPlanCustomInfoVO> selectActionPlanInfo(@ApiParam(value = "执行方案code", required = true) @RequestParam String actionCode) {
        ActionPlanCustomInfoVO actionPlanCustomInfoVO = actionPlanService.selectActionPlanInfo(actionCode);
        return R.data(actionPlanCustomInfoVO);
    }


    /**
     * 父方案保存
     *
     * @param acm 客户资料业务对象
     * @return R
     */
    @ApiLog("父方案保存")
    @ApiOperationSupport(order = 7)
    @PostMapping("/saveAdditionalCustomerMaterials")
    @ApiOperation(value = "父方案保存", notes = "传入customerMaterialsBO")
    public R saveAdditionalCustomerMaterials(@RequestBody AdditionalCustomerMaterialsBO acm) {
        return R.status(actionPlanService.saveAdditionalCustomerMaterials(acm));
    }

    /**
     * 修改执行方案
     *
     * @param actionPlanCustomInfoVO 客户资料业务对象
     * @return R
     */
    @ApiLog("父方案修改")
    @ApiOperationSupport(order = 7)
    @PostMapping("/modifyAdditionalCustomerMaterials")
    @ApiOperation(value = "父方案修改", notes = "传入actionPlanCustomInfoVO")
    public R updateAdditionalCustomerMaterials(@RequestBody ActionPlanCustomInfoVO actionPlanCustomInfoVO) {
        return R.status(actionPlanService.updateAdditionalCustomerMaterials(actionPlanCustomInfoVO));
    }


    /**
     * 系统人员查询融资方案
     *
     * @param searchInfo: planCode,planName,companyName,flow,startDate,endDate
     * @param query       分页信息
     * @return R
     */
    @ApiOperationSupport(order = 8)
    @PostMapping("/getActionPlans")
    @ApiOperation(value = "系统人员查询融资方案", notes = "传入searchInfo")
    public R getActionPlans(@RequestBody Map<String, Object> searchInfo, @RequestBody Query query) {
        if (searchInfo == null) {
            return R.fail("参数有误");
        }
        if (StringUtils.isEmpty((String) searchInfo.get("sysUserId"))) {
            return R.fail("id不能为空。");
        }
        String sysUserId = "sys_" + searchInfo.get("sysUserId");

        //向流程记录表里面查询该用户管理过哪些方案表 plan_1,chil_2 (去重查询)
        List<String> planCodes = transferRecordService.getActionPlanId(sysUserId);

        //方案id
        List<String> list = new ArrayList<>();
        //子方案id
        List<String> list2 = new ArrayList<>();

        planCodes.forEach(planCode -> {
            if (planCode.startsWith("plan_")) {
                list.add(planCode.substring(5, planCode.length()));
            } else {
                list2.add(planCode.substring(5, planCode.length()));
            }
        });

        if (!CollectionUtils.isEmpty(list2)) {
            Map<String, Object> map = new HashMap<>();
            map.put("childPlanIds", list2);
            //根据子方案ID集合查询所有方案ID
            List<ActionChildPlan> childPlans = actionChildPlanService.findSearch(map);
            //追加并去重
            childPlans.forEach(childPlan -> list.add(String.valueOf(childPlan.getPlanId())));
            List<String> actionPlanIds = list.stream().distinct().collect(Collectors.toList());
            searchInfo.put("ids", actionPlanIds);
        } else {
            searchInfo.put("ids", list);
        }
        Page<ActionPlan> pages = actionPlanService.findSearch(searchInfo, query);
        return R.data(ActionPlanWrapper.build().pageVO(pages));
    }

    /**
     * 查询所有子方案
     */
    @PostMapping("/findAllChildPlanById")
    @ApiOperationSupport(order = 9)
    @ApiOperation(value = "风控审核", notes = "传入子方案父Id")
    public R<PageResult<ActionChildPlanVO>> findAllChildPlanById(@ApiParam(value = "执行方案Id") @RequestBody ActionPlan actionPlan,
                                                                 @ApiParam(value = "查询对象") @RequestBody Query query) {
        // 父方案id
        if (StringUtils.isEmpty(actionPlan.getId())) {
            // TODO 抛异常
        }

        PageResult<ActionChildPlanVO> actionChildPlanVOS = actionChildPlanService.findAllChildForPage(actionPlan.getId(),
                QueryUtil.queryIfNullForNew(query).getPageNo(),
                QueryUtil.queryIfNullForNew(query).getPageSize());
        return R.data(actionChildPlanVOS);
    }


    /**
     * 修改状态
     */
    @ApiLog("修改状态")
    @ApiOperationSupport(order = 10)
    @PostMapping(value = "/updateStatusById")
    @ApiOperation(value = "更新状态", notes = "传入id和状态")
    public R updateStatusById(@RequestBody ActionPlan plan) {
        actionPlanService.updateStatus(plan.getId(), plan.getStatus());
        return R.success(ResultCode.SUCCESS);
    }


    /**
     * 根据融资预案id获取C端融资预案详情（PDF转图片）
     */
    @ApiLog("融资预案详情图片")
    @ApiOperationSupport(order = 10)
    @ResponseBody
    @ApiOperation(value = "融资预案详情图片", notes = "传入finPlanCode")
    @GetMapping(value = "/get", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] planDetailsImage(@ApiParam(value = "融资预案id", required = true) @RequestParam String finPlanCode) throws IOException {
        File file1 = ResourceUtils.getFile("classpath:plan.png");
        FileInputStream inputStream = new FileInputStream(file1);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }


}
