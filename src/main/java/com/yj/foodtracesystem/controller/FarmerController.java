package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.repository.UserRepository;
import com.yj.foodtracesystem.service.FarmerService;
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
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        /*List<User> list=userService.findAllUser();
        session.setAttribute("list",list);
        modelAndView.addObject("userList",list);*/
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        List<FiledOperationType> operationTypeList = farmerService.findAllFiledOperationType();
        modelAndView.addObject("filedInfoList", filedInfoList);
        modelAndView.addObject("operationTypeList", operationTypeList);
        OperationHisPara operationHisPara = new OperationHisPara();
        modelAndView.addObject("operationHisPara", operationHisPara);
       /* operationHisPara.startTime = "2018-05-01 00:00";
        operationHisPara.endTime = "2018-05-11 00:00";
        operationHisPara.filedId = 0;
        List<OperationHisResult> operationHisResList = farmerService.findOperaHisByPara(operationHisPara);
        modelAndView.addObject("operationHisResList", operationHisResList);*/
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

   /* @RequestMapping(value = "/farmer/queryHisCoop", method = RequestMethod.POST)
    @ResponseBody
    public List<OperationHisResult> queryHisCoop(OperationHisPara operationHisPara) throws Exception {
        List<OperationHisResult> operationHisResList = farmerService.findOperaHisByPara(operationHisPara);
        return operationHisResList;
    }*/

    @PostMapping(value = "/farmer/queryHisCoop")
    public ModelAndView queryHisCoop(OperationHisPara operationHisPara) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        List<FiledOperationType> operationTypeList = farmerService.findAllFiledOperationType();
        modelAndView.addObject("filedInfoList", filedInfoList);
        modelAndView.addObject("operationTypeList", operationTypeList);
        List<OperationHisResult> operationHisResList = farmerService.findOperaHisByPara(operationHisPara);
        modelAndView.addObject("operationHisResList",operationHisResList);
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

    @GetMapping("/farmer/test")
    public String test() {
        return "farmer/test";
    }

}
