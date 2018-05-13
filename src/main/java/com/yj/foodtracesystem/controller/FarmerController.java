package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationHisPara;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.repository.UserRepository;
import com.yj.foodtracesystem.service.FarmerService;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 10:00 2018/5/1
 */
@Controller
public class FarmerController {
    @Autowired
    private UserService userService;
    @Autowired
    private FarmerService farmerService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublicService publicService;

    @RequestMapping(value = "/farmer/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("farmer/header");
        return modelAndView;
    }

    @GetMapping("/farmer/filedMan")
    public ModelAndView filedMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("farmer/filedMan");
        return modelAndView;
    }

    @GetMapping("/farmer/seedMan")
    public ModelAndView seedMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }

    @GetMapping("/farmer/cultMan")
    public ModelAndView culMan(HttpSession session) throws Exception {
        ModelAndView modelAndView = initialFarmerPage();
        OperationHisPara operationHisPara = new OperationHisPara();
        modelAndView.addObject("operationHisPara", operationHisPara);
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/queryHisCoop")
    public ModelAndView queryHisCoop(OperationHisPara operationHisPara) throws Exception {
        ModelAndView modelAndView = initialFarmerPage();
        List<OperationHisResult> operationHisResList = farmerService.findOperaHisByPara(operationHisPara);
        modelAndView.addObject("operationHisResList", operationHisResList);
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/addFiledOpera")
    public ModelAndView addFiledOpera(FiledOperation filedOperation) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        filedOperation.setUserId(Integer.parseInt(user.getUserNum()));
        filedOperation.setOperateTime(publicService.getStringDate());
        farmerService.saveFiledOperation(filedOperation);
        ModelAndView modelAndView = initialFarmerPage();
        OperationHisPara operationHisPara = new OperationHisPara();
        modelAndView.addObject("operationHisPara", operationHisPara);
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

    private ModelAndView initialFarmerPage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getUserNum() + ")");
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        List<FiledOperationType> operationTypeList = farmerService.findAllFiledOperationType();
        modelAndView.addObject("filedInfoList", filedInfoList);
        modelAndView.addObject("operationTypeList", operationTypeList);
        FiledOperation filedOperation = new FiledOperation();
        modelAndView.addObject("filedOperation", filedOperation);
        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList", seedInfoList);
        return modelAndView;
    }

}
