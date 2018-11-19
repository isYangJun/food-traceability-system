package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.SaleInfo;
import com.yj.foodtracesystem.model.TempModel.ProductPara;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.service.*;
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
    @Autowired
    private CoopService coopService;

    @Autowired
    private TransporterService transporterService;

    /*
     * 销售管理员*/
    @GetMapping(value = "/saleadmin/saleAdminMan")
    public ModelAndView operatorMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "welcome " + user.getUserCompName() + ":" + user.getName() + "(" + user.getUserNum() + ")");
        modelAndView.addObject("saleOperaInfo", new User());
        modelAndView.addObject("saleOperaInfoById", new User());
        modelAndView.addObject("saleOperaInfoByTime", new QueryPara());
        modelAndView.setViewName("saleadmin/saleAdminMan");
        return modelAndView;
    }


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
        saleInfo.setProBatchNum(coopService.findProBatchNumByProNum(saleInfo.getProNum()));

        int preWeight=transporterService.findProWeightByProNumAndDestinationNum(user.getUserComp(),saleInfo.getProNum());
        if(publicService.isReasonableProWeight(preWeight,saleInfo.getProWeight())){
            double grossLossRate=saleInfo.getProWeight()*1.0/preWeight;
            grossLossRate=((int)(grossLossRate*1000))/1000.0;
            saleInfo.setGrossLossRate(grossLossRate);
            saleService.saveSaleInfo(saleInfo);
            initialModel(modelAndView, user);
            modelAndView.setViewName("saleman/saleMan");
            return modelAndView;
        }else {
            ModelAndView modelAndView1 =new ModelAndView();
            modelAndView1.setViewName("errorProweight");
            return modelAndView1;
        }
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
        modelAndView.addObject("proWeightById", new SaleInfo());
        List<ProductPara> proNumNameList = publicService.findSaleProFromTransAndRepos(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        modelAndView.setViewName("saleman/verifyPro");
        return modelAndView;
    }
    @PostMapping(value = "/saleman/verifyProWeightById")
    public ModelAndView verifyProWeightById(SaleInfo proWeightById) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        String proBatchNum = coopService.findProBatchNumByProNum(proWeightById.getProNum());
        String verifyRes = saleService.countProWeightByProBatchNum(proBatchNum);
        modelAndView.addObject("verifyRes",verifyRes);
        modelAndView.addObject("proWeightById", new SaleInfo());
        List<ProductPara> proNumNameList = publicService.findSaleProFromTransAndRepos(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        modelAndView.setViewName("saleman/verifyPro");
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
