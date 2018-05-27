package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.service.TransporterService;
import com.yj.foodtracesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:28 2018/5/26
 */
@Controller
public class TransController {

    @Autowired
    TransporterService transporterService;
    @Autowired
    UserService userService;

    @GetMapping("/transporter/transMan")
    public ModelAndView addTrans() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        modelAndView.addObject("addTransInfo", new TransportInfo());
        List<ProductInfo> proNumNameList = transporterService.findTranstedProductInfo();
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<TransStationInfo> transStationInfo = transporterService.findTransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);

        modelAndView.addObject("startStationNum", user.getUserComp());
        modelAndView.addObject("startStationName", user.getUserCompName());
        List<ComInfo> comInfoList=transporterService.findTransOrReposInfo();
        modelAndView.addObject("comInfoList", comInfoList);
        modelAndView.addObject("transInfoById", new TransportInfo());
        modelAndView.addObject("transInfoByTime", new QueryPara());

        modelAndView.setViewName("transporter/transMan");
        return modelAndView;
    }

}
