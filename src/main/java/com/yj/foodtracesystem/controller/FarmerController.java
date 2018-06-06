package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationHerPara;
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
        modelAndView.addObject("userName", "Welcome " +user.getUserCompName()+": "+ user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("farmer/header");
        return modelAndView;
    }

    /************************************种子管理***********************************************/
    @GetMapping("/farmer/seedMan")
    public ModelAndView seedMan() {
        ModelAndView modelAndView = initialSeedMan();
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }
    @PostMapping(value ="/farmer/querySeedInfoByTime")
    public ModelAndView querySeedInfoByTime(OperationHerPara operationHerPara) throws Exception {
        ModelAndView modelAndView = initialSeedMan();
        List<SeedInfo> seedInfoResList = farmerService.findSeedByTime(operationHerPara.startTime,operationHerPara.endTime);
        modelAndView.addObject("seedInfoResList",seedInfoResList);
        modelAndView.setViewName("farmer/seedMan");
        return  modelAndView;
    }
    @PostMapping(value="/farmer/querySeedInfoById")
    public  ModelAndView querySeedInfoById(OperationHerPara operationHerPara) throws Exception{
        ModelAndView modelAndView=initialSeedMan();
        List<SeedInfo> seedInfoResList = farmerService.findSeedInfoById(operationHerPara.seedId);
        modelAndView.addObject("seedInfoResList",seedInfoResList);
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }
    @PostMapping(value = "/farmer/addSeedInfo")
    public ModelAndView addSeedInfo(SeedInfo seedInfo) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        seedInfo.setSeedRegTime(publicService.getStringDate());
        seedInfo.setOperatorNum(user.getUserNum());
        farmerService.saveSeedInfo(seedInfo);
        ModelAndView modelAndView = initialSeedMan();
        modelAndView.setViewName("farmer/seedMan");
        return modelAndView;
    }

    private ModelAndView initialSeedMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName","welcome "+ user.getUserCompName() + ":" + user.getName() + "(" +user.getUserNum()+ ")");
        SeedInfo seedInfo = new SeedInfo();
        modelAndView.addObject("seedInfo",seedInfo);
        List<SeedInfo> seedInfoList = farmerService.findAllSeedInfo();
        modelAndView.addObject("seedInfoList",seedInfoList);
        OperationHerPara operationHerPara = new OperationHerPara();
        modelAndView.addObject("operationHerPara",operationHerPara);
        return modelAndView;
    }

    /*****************************************地块管理记录******************************************/
    @GetMapping("/farmer/filedMan")
    public ModelAndView filedMan() {
        ModelAndView modelAndView = initialFiledMan();
        modelAndView.setViewName("farmer/filedMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/queryFiledInfoByTime")
    public ModelAndView queryFiledInfoByTime(OperationHisPara operationHisPara) throws Exception {
        ModelAndView modelAndView = initialFiledMan();
        List<FiledInfo> filedInfoResList=farmerService.findByTime(operationHisPara.startTime,operationHisPara.endTime);
        modelAndView.addObject("filedInfoResList",filedInfoResList);
        modelAndView.setViewName("farmer/filedMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/queryFiledInfoById")
    public ModelAndView queryFiledInfoById(OperationHisPara operationHisPara) throws Exception {
        ModelAndView modelAndView = initialFiledMan();
        List<FiledInfo> filedInfoResList=farmerService.findFiledInfoById(operationHisPara.filedId);
        modelAndView.addObject("filedInfoResList",filedInfoResList);
        modelAndView.setViewName("farmer/filedMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/addFiledInfo")
    public ModelAndView addFiledInfo(FiledInfo filedInfo) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        filedInfo.setFiledCompNum(user.getUserComp());
        filedInfo.setFiledCompName(user.getUserCompName());
        filedInfo.setFiledRegTime(publicService.getStringDate());
        filedInfo.setOperatorNum(user.getUserNum());
        farmerService.saveFiledInfo(filedInfo);
        ModelAndView modelAndView = initialFiledMan();
        modelAndView.setViewName("farmer/filedMan");
        return modelAndView;
    }

    private ModelAndView initialFiledMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome "+user.getUserCompName()+": " + user.getName() + " (" + user.getUserNum() + ")");
        FiledInfo filedInfo = new FiledInfo();
        modelAndView.addObject("filedInfo", filedInfo);
        List<FiledInfo> filedInfoList = farmerService.findAllFiledInfo();
        modelAndView.addObject("filedInfoList", filedInfoList);
        OperationHisPara operationHisPara = new OperationHisPara();
        modelAndView.addObject("operationHisPara", operationHisPara);
        return modelAndView;
    }

    /*****************************************种植管理记录******************************************/
    @GetMapping("/farmer/cultMan")
    public ModelAndView culMan(HttpSession session) throws Exception {
        ModelAndView modelAndView = initialCultManPage();
        OperationHisPara operationHisPara = new OperationHisPara();
        modelAndView.addObject("operationHisPara", operationHisPara);
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
        ModelAndView modelAndView = initialCultManPage();
        OperationHisPara operationHisPara = new OperationHisPara();
        modelAndView.addObject("operationHisPara", operationHisPara);
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

    @PostMapping(value = "/farmer/queryHisCoop")
    public ModelAndView queryHisCoop(OperationHisPara operationHisPara) throws Exception {
        ModelAndView modelAndView = initialCultManPage();
        List<OperationHisResult> operationHisResList = farmerService.findOperaHisByPara(operationHisPara);
        modelAndView.addObject("operationHisResList", operationHisResList);
        modelAndView.setViewName("farmer/cultMan");
        return modelAndView;
    }

    private ModelAndView initialCultManPage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome "+user.getUserCompName()+": " + user.getName() + " (" + user.getUserNum() + ")");
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
