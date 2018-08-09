package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.Result.Result;
import com.yj.foodtracesystem.Result.ResultEnum;
import com.yj.foodtracesystem.Result.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationHerPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.repository.OperationOrderInfoRepository;
import com.yj.foodtracesystem.repository.UserRepository;
import com.yj.foodtracesystem.service.CoopService;
import com.yj.foodtracesystem.service.FarmerService;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 10:00 2018/5/1
 */
@RestController
public class FarmerController {
    @Autowired
    private UserService userService;
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublicService publicService;

    @Autowired
    private OperationOrderInfoRepository operationOrderInfoRepository;

    @Autowired
    private CoopService coopService;

    private static final Logger logger = LoggerFactory.getLogger(FarmerController.class);

    @GetMapping(value = "/farmer/test")
    public String test() {
        logger.info("farmer test");
        return "hello world, I yangJun";
    }

    @GetMapping(value = "/farmer/home")
    public String home() {
        logger.info("farmer home");
        return "farmer home";
    }


    @PostMapping(value = "/farmer/updateOrder")
    public Result<OperationOrderInfo> updateOrder(@RequestBody OperationOrderInfo orderInfo) {
        try{
            OperationOrderInfo operationOrderInfo = farmerService.updateOrderInfo(orderInfo.getId());
            return ResultUtil.success(operationOrderInfo);
        }catch (NullPointerException e) {
           throw new BaseException(ResultEnum.ARGUMENT_ERROR);
        }
    }

    /************************************种子管理***********************************************/

    @PostMapping(value = "/farmer/querySeedInfoByTime")
    public ModelAndView querySeedInfoByTime(OperationHerPara operationHerPara) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<SeedInfo> seedInfoResList = farmerService.findSeedByTime(operationHerPara.startTime, operationHerPara.endTime);
        modelAndView.addObject("seedInfoResList", seedInfoResList);
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/querySeedInfoById")
    public ModelAndView querySeedInfoById(OperationHerPara operationHerPara) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<SeedInfo> seedInfoResList = farmerService.findSeedInfoById(operationHerPara.seedId);
        modelAndView.addObject("seedInfoResList", seedInfoResList);
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/addSeedInfo")
    public ModelAndView addSeedInfo(SeedInfo seedInfo) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        seedInfo.setSeedRegTime(publicService.getStringDate());
        seedInfo.setOperatorNum(user.getUserNum());
        farmerService.saveSeedInfo(seedInfo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }
}
