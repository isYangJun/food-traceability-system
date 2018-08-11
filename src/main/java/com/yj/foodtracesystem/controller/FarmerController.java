package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.Result.Result;
import com.yj.foodtracesystem.Result.ResultEnum;
import com.yj.foodtracesystem.Result.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationHerPara;
import com.yj.foodtracesystem.service.FarmerService;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 10:00 2018/5/1
 */
@RestController
@RequestMapping("/farmer")
@Api(value = "farmer controller", description = "farmer controller")
public class FarmerController {
    @Autowired
    private UserService userService;
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private PublicService publicService;

    private static final Logger logger = LoggerFactory.getLogger(FarmerController.class);

    @ApiOperation(value = "API farmer test")
    @GetMapping(value = "/test")
    public String test() {
        logger.debug("farmer test");
        return "farmer test";
    }


    @ApiOperation(value = "API 更新订单状态")
    @PostMapping(value = "/updateOrder")
    public Result<OperationOrderInfo> updateOrder(@RequestBody OperationOrderInfo orderInfo) {
        try{
            OperationOrderInfo operationOrderInfo = farmerService.updateOrderInfo(orderInfo.getId());
            return ResultUtil.success(operationOrderInfo);
        }catch (NullPointerException e) {
           throw new BaseException(ResultEnum.ARGUMENT_ERROR);
        }
    }

    /************************************种子管理***********************************************/

    @ApiOperation(value = "query seed info by time")
    @PostMapping(value = "/querySeedInfoByTime")
    public Result<List<SeedInfo>> querySeedInfoByTime(@RequestBody OperationHerPara operationHerPara) {
        List<SeedInfo> seedInfoResList = farmerService.findSeedByTime(operationHerPara.startTime, operationHerPara.endTime);
        return ResultUtil.success(seedInfoResList);
    }

    @ApiOperation(value = "query seed info by id")
    @PostMapping(value = "/querySeedInfoById")
    public Result<List<SeedInfo>> querySeedInfoById( @RequestBody OperationHerPara operationHerPara) {
        List<SeedInfo> seedInfoResList = farmerService.findSeedInfoById(operationHerPara.seedId);
        return ResultUtil.success(seedInfoResList);
    }

    @ApiOperation(value = "add seed info ")
    @PostMapping(value = "/addSeedInfo")
    public Result<SeedInfo> addSeedInfo(@RequestBody SeedInfo seedInfo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        seedInfo.setSeedRegTime(publicService.getStringDate());
        seedInfo.setOperatorNum(user.getUserNum());
        return ResultUtil.success(farmerService.saveSeedInfo(seedInfo));
    }
}
