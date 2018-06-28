package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.OperationHerPara;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.service.CoopService;
import com.yj.foodtracesystem.service.PublicService;
import com.yj.foodtracesystem.service.TransporterService;
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
 * @Date: Created in 16:28 2018/5/26
 */
@Controller
public class TransController {

    @Autowired
    TransporterService transporterService;
    @Autowired
    UserService userService;

    @Autowired
    CoopService coopService;

    @Autowired
    PublicService publicService;

    /****************************************************运输信息管理******************************************************/
    @GetMapping("/transporter/transMan")
    public ModelAndView transMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        initialParaModel(modelAndView, user);
        modelAndView.setViewName("transporter/transMan");
        return modelAndView;
    }


    @PostMapping(value = "/transporter/addTransInfo")
    public ModelAndView addTransInfo(TransportInfo addTransInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        addTransInfo.setComNum(user.getUserComp());
        addTransInfo.setComName(user.getUserCompName());
        ProductInfo productInfo = coopService.findByProductNum(addTransInfo.getProNum());
        addTransInfo.setProName(productInfo.getProName());
        addTransInfo.setInRecorded(1);
        List<ComInfo> comInfoList = coopService.findComInfoByComNum(addTransInfo.getDestinationNum());
        addTransInfo.setDestinationName(comInfoList.get(0).getComName());
        addTransInfo.setOperatorNum(user.getUserNum());
        addTransInfo.setRecordedTime(publicService.formatTime(addTransInfo.getRecordedTime()));
        transporterService.saveTransInfo(addTransInfo);
        initialParaModel(modelAndView, user);
        modelAndView.setViewName("transporter/transMan");
        return modelAndView;
    }

    @PostMapping(value = "/transporter/transInfoById")
    public ModelAndView transInfoById(TransportInfo transInfoById) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<TransportInfo> transInfoResList = transporterService.findTransInfoByProNum(transInfoById.getProNum());
        modelAndView.addObject("transInfoResList", transInfoResList);
        initialParaModel(modelAndView, user);
        modelAndView.setViewName("transporter/transMan");
        return modelAndView;
    }

    @PostMapping(value = "/transporter/transInfoByTime")
    public ModelAndView transInfoByTime(QueryPara queryPara) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<TransportInfo> transInfoResList = transporterService.findTransInfoByTime(queryPara.getStartTime(), queryPara.getEndTime());
        modelAndView.addObject("transInfoResList", transInfoResList);
        initialParaModel(modelAndView, user);
        modelAndView.setViewName("transporter/transMan");
        return modelAndView;
    }

    private void initialParaModel(ModelAndView modelAndView, User user) {
        modelAndView.addObject("addTransInfo", new TransportInfo());
        List<ProductInfo> proNumNameList = transporterService.findTranstedProductInfo();
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<TransStationInfo> transStationInfo = transporterService.findTransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);
        modelAndView.addObject("startStationNum", user.getUserComp());
        modelAndView.addObject("startStationName", user.getUserCompName());
        List<ComInfo> comInfoList = transporterService.findTransOrReposInfo();
        modelAndView.addObject("comInfoList", comInfoList);
        modelAndView.addObject("transInfoById", new TransportInfo());
        modelAndView.addObject("transInfoByTime", new QueryPara());
    }

    /***************************************************运输站点管理****************************************************/
    @GetMapping(value = "/transadmin/transStationMan")
    public ModelAndView transStationMan() {
        ModelAndView modelAndView = initialTransStationMan();
        modelAndView.setViewName("transadmin/transStationMan");
        return modelAndView;
    }

    @PostMapping(value = "/transadmin/addTransStationInfo")
    public ModelAndView addTransStationInfo(TransStationInfo addTransStationInfo) {
        ModelAndView modelAndView = initialTransStationMan();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        addTransStationInfo.setOperatorNum(user.getUserNum());
        addTransStationInfo.setCarRegTime(publicService.getStringDate());
        transporterService.saveTransStationInfo(addTransStationInfo);
        modelAndView.setViewName("transadmin/transStationMan");
        return modelAndView;
    }

    @PostMapping(value = "/transadmin/transStationInfoById")
    public ModelAndView transStationInfoById(TransStationInfo transStationInfoById) {
        ModelAndView modelAndView =initialTransStationMan();
        List<TransStationInfo> transStationInfoResList = transporterService.findTransStationInfoByCarNum(transStationInfoById.getCarNum());
        modelAndView.addObject("transStationInfoResList", transStationInfoResList);
        modelAndView.setViewName("transadmin/transStationMan");
        return modelAndView;
    }

    @PostMapping(value = "/transadmin/transStationInfoByTime")
    public ModelAndView transStationInfoByTime(QueryPara queryPara) {
        ModelAndView modelAndView = initialTransStationMan();
        List<TransStationInfo> transStationInfoResList = transporterService.findTransStationInfoByTime(queryPara.getStartTime(), queryPara.getEndTime());
        modelAndView.addObject("transStationInfoResList", transStationInfoResList);
        modelAndView.setViewName("transadmin/transStationMan");
        return modelAndView;
    }

    private ModelAndView initialTransStationMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "welcome " + user.getUserCompName() + ":" + user.getName() + "(" + user.getUserNum() + ")");
        TransStationInfo transStationInfo = new TransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);
        List<Car> carList = transporterService.findCarInfo();
        modelAndView.addObject("carList", carList);
        List<TransStationInfo> transStationInfoList = transporterService.findAllInfo();
        modelAndView.addObject("transStationInfoList", transStationInfoList);
        QueryPara queryPara = new QueryPara();
        modelAndView.addObject("queryPara", queryPara);
        return modelAndView;

    }
}