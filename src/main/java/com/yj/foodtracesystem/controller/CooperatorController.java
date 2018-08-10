package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.Result.Result;
import com.yj.foodtracesystem.Result.ResultUtil;
import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.repository.FiledInfoRepository;
import com.yj.foodtracesystem.repository.FiledOperationTypeRepository;
import com.yj.foodtracesystem.service.CoopService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 19:27 2018/5/16
 */
@RestController
@RequestMapping("/cooperator")
@Api(value = "cooperator controller", description = "cooperator controller")
public class CooperatorController {
    @Autowired
    private UserService userService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private CoopService coopService;
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private FiledInfoRepository filedInfoRepository;

    @Autowired
    private FiledOperationTypeRepository filedOperationTypeRepository;

    private static final Logger logger=LoggerFactory.getLogger(CooperatorController.class);
    @ApiOperation(value = "API cooperator test")
    @GetMapping(value = "/test")
    public String test() {
        logger.info("cooperator test");
        return "cooperator test";
    }


    @ApiOperation(value = "API add order")
    @PostMapping(value = "/addOrder")
    public Result<OperationOrderInfo> addOrder(@RequestBody OperationOrderInfo operationOrderInfo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        operationOrderInfo.setFiledName(filedInfoRepository.findById(operationOrderInfo.getFiledId()).getFiledName());
        operationOrderInfo.setCreaterId(user.getUserNum());
        operationOrderInfo.setCreaterName(user.getName());
        operationOrderInfo.setIsDone(0);
        operationOrderInfo.setOperationName(filedOperationTypeRepository.findById(operationOrderInfo.getOperateTypeId()).getOperationName())
        ;
        operationOrderInfo.setUserName(userService.findUserByUserNum(operationOrderInfo.getUserId()).getName());
        operationOrderInfo.setOrderTime(publicService.getStringDate());
        operationOrderInfo.setSeedName(farmerService.findBySeedId(operationOrderInfo.getSeedId()).getSeedName());
        return ResultUtil.success(coopService.saveOperationOrderInfo(operationOrderInfo));
    }

    /*
     * **************************生产者管理*************************/

    @ApiOperation(value = "add farmer")
    @PostMapping("/addFarmer")
    public Result<User> addFarmer(@RequestBody User farmerInfo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        farmerInfo.setActive(1);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        farmerInfo.setPassword("123456");
        farmerInfo.setUserCompName(user.getUserCompName());
        farmerInfo.setUserComp(user.getUserComp());
        farmerInfo.setRegTime(publicService.getStringDate());
        Role role = new Role();
        role.setRole("2");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        farmerInfo.setRoles(roles);
        return ResultUtil.success(userService.saveUser(farmerInfo));
    }

    @ApiOperation(value = "query farmer by id")
    @PostMapping("/queryFarmerById")
    public Result<List<User>> queryFarmerById(User farmerInfoById) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        List<User> farmerInfoResList = userService.findByUserNumOrName(farmerInfoById.getName(), farmerInfoById.getUserNum(), user.getUserComp());
        return ResultUtil.success(farmerInfoResList);
    }

    @ApiOperation(value = "query farmer by time")
    @PostMapping("/queryFarmerByTime")
    public Result<List<User>> queryFarmerByTime(QueryPara farmerInfoByTime) {
        String startTime = farmerInfoByTime.getStartTime();
        String endTime = farmerInfoByTime.getEndTime();
        List<User> farmerInfoResList = userService.findByTime(startTime, endTime);
        return ResultUtil.success(farmerInfoResList);
    }


}
