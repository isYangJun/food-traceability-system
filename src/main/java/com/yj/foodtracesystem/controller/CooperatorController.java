package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.repository.FiledInfoRepository;
import com.yj.foodtracesystem.repository.FiledOperationTypeRepository;
import com.yj.foodtracesystem.service.CoopService;
import com.yj.foodtracesystem.service.FarmerService;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.UserService;
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
@Controller
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
    @RequestMapping(value = "/cooperator/hom", method = RequestMethod.GET)
    @ResponseBody
    public String coophome() {
        logger.info("coophome home");
        return "coophome home";
    }



    @PostMapping(value = "/cooperator/addOrder")
    public ModelAndView addOrder(OperationOrderInfo operationOrderInfo) throws Exception {
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
        coopService.saveOperationOrderInfo(operationOrderInfo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("opeartionOrderInfo", new OperationOrderInfo());

        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);

        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);

        List<FiledOperationType> operationTypeList = farmerService.findAllFiledOperationType();
        modelAndView.addObject("operationTypeList", operationTypeList);

        List<User> userInfoList = coopService.findByUserCompAndRole(user.getUserComp(), 2);
        modelAndView.addObject("userInfoList", userInfoList);

        List<OperationOrderInfo> operationOrderInfoList = coopService.findAllOperationOrderInfo();
        modelAndView.addObject("orderInfoResList", operationOrderInfoList);

        modelAndView.setViewName("cooperator/orderMan");
        return modelAndView;
    }

    /*
     * **************************生产者管理*************************/
    @GetMapping(value = "/cooperator/farmerMan")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        User farmerInfo = new User();
        User farmerInfoById = new User();
        QueryPara farmerInfoByTime = new QueryPara();
        modelAndView.addObject("farmerInfo", farmerInfo);
        modelAndView.addObject("farmerInfoById", farmerInfoById);
        modelAndView.addObject("farmerInfoByTime", farmerInfoByTime);
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/addFarmer")
    public ModelAndView addFarmer(User farmerInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
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
        userService.saveUser(farmerInfo);
        modelAndView.addObject("farmerInfo", new User());
        modelAndView.addObject("farmerInfoById", new User());
        modelAndView.addObject("farmerInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/queryFarmerById")
    public ModelAndView queryFarmerById(User farmerInfoById) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<User> farmerInfoResList = userService.findByUserNumOrName(farmerInfoById.getName(), farmerInfoById.getUserNum(), user.getUserComp());
        modelAndView.addObject("farmerInfoResList", farmerInfoResList);
        modelAndView.addObject("farmerInfo", new User());
        modelAndView.addObject("farmerInfoById", new User());
        modelAndView.addObject("farmerInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }

    @PostMapping("/cooperator/queryFarmerByTime")
    public ModelAndView queryFarmerByTime(QueryPara farmerInfoByTime) {
        ModelAndView modelAndView = new ModelAndView();
        String startTime = farmerInfoByTime.getStartTime();
        String endTime = farmerInfoByTime.getEndTime();
        List<User> farmerInfoResList = userService.findByTime(startTime, endTime);
        modelAndView.addObject("farmerInfoResList", farmerInfoResList);
        modelAndView.addObject("farmerInfo", new User());
        modelAndView.addObject("farmerInfoById", new User());
        modelAndView.addObject("farmerInfoByTime", new QueryPara());
        modelAndView.setViewName("cooperator/farmerMan");
        return modelAndView;
    }


}
