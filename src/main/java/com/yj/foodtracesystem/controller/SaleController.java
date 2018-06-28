package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.SaleInfo;
import com.yj.foodtracesystem.model.TempModel.ProductPara;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.SaleService;
import com.yj.foodtracesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 20:37 2018/6/3
 */
@Controller
public class SaleController {
    @Autowired
    private UserService userService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SaleService saleService;

    @GetMapping("/saleman/saleMan")
    public ModelAndView saleMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        initialModel(modelAndView, user);
        modelAndView.setViewName("saleman/saleMan");
        return modelAndView;
    }

    @PostMapping(value = "/saleman/addSaleInfo")
    public ModelAndView addSaleInfo(SaleInfo saleInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        saleInfo.setComName(saleService.findComNameByComNum(user.getUserComp()));
        saleInfo.setProName(saleService.findProNameByProNum(saleInfo.getProNum()));
        saleInfo.setInRecorded(1);
        saleInfo.setOperatorNum(user.getUserNum());
        saleInfo.setRecordedTime(publicService.formatTime(saleInfo.getRecordedTime()));
        saleService.saveSaleInfo(saleInfo);

        initialModel(modelAndView, user);

        modelAndView.setViewName("saleman/saleMan");
        return modelAndView;
    }

    @PostMapping(value = "/saleman/saleInfoById")
    public ModelAndView saleInfoById(SaleInfo saleInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<SaleInfo> saleInfoResList = saleService.findSaleInfoByProNum(saleInfo.getProNum());
        modelAndView.addObject("saleInfoResList", saleInfoResList);

        initialModel(modelAndView, user);

        modelAndView.setViewName("saleman/saleMan");
        return modelAndView;
    }

    @PostMapping(value = "/saleman/saleInfoByTime")
    public ModelAndView saleInfoByTime(QueryPara queryPara) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<SaleInfo> saleInfoResList = saleService.findSaleInfoByTime(queryPara.getStartTime(), queryPara.getEndTime());
        modelAndView.addObject("saleInfoResList", saleInfoResList);

        initialModel(modelAndView, user);

        modelAndView.setViewName("saleman/saleMan");
        return modelAndView;
    }

    @GetMapping(value = "/saleman/verifyPro")
    public ModelAndView verifyProWeight() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<ProductPara> proNumNameList = publicService.findSaleProFromTransAndRepos(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        return modelAndView;
    }

    @PostMapping(value = "/saleman/verifyProWeightById")
    public ModelAndView verifyProWeightById(SaleInfo proWeightById) {
        countProTotalWeight(String proNum);
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");

        return modelAndView;
    }

    private void initialModel(ModelAndView modelAndView, User user) {
        modelAndView.addObject("comNum", user.getUserComp());
        modelAndView.addObject("comName", user.getUserCompName());
        List<ProductPara> proNumNameList = publicService.findSaleProFromTransAndRepos(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        modelAndView.addObject("addSaleInfo", new SaleInfo());
        modelAndView.addObject("saleInfoById", new SaleInfo());
        modelAndView.addObject("saleInfoByTime", new QueryPara());
    }

}
