package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.*;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
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
 * @Date: Created in 21:54 2018/5/30
 */
@Controller
public class RepositoryController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReposService reposService;
    @Autowired
    private TransporterService transporterService;
    @Autowired
    private CoopService coopService;
    @Autowired
    private PublicService publicService;

    @GetMapping("/repository/repositoryMan")
    public ModelAndView repositoryMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        initalModelView(modelAndView, user);
        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/addReposInfo")
    public ModelAndView addReposInfo(RepositoryInfo repositoryInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        repositoryInfo.setRepositoryNum(user.getUserComp());
        repositoryInfo.setInRecorded(1);
        repositoryInfo.setWarehouseName(reposService.findWareNameByWarehouseNum(repositoryInfo.getWarehouseNum()));
        repositoryInfo.setProName(reposService.findByProNum(repositoryInfo.getProNum()));
        repositoryInfo.setSaveTemp(reposService.findSaveTempByWarehouseNum(repositoryInfo.getWarehouseNum()));
        repositoryInfo.setOperatorNum(user.getUserNum());
        repositoryInfo.setRecordedTime(publicService.formatTime(repositoryInfo.getRecordedTime()));
        repositoryInfo.setProBatchNum(coopService.findProBatchNumByProNum(repositoryInfo.getProNum()));
        reposService.saveRepositoryInfo(repositoryInfo);
        initalModelView(modelAndView, user);
        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/reposInfoById")
    public ModelAndView reposInfoById(RepositoryInfo repositoryInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        initalModelView(modelAndView, user);
        List<RepositoryInfo> repositoryInfoList = reposService.findRepositoryInfoByProNum(repositoryInfo.getProNum());
        modelAndView.addObject("repositoryInfoList", repositoryInfoList);
        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/reposInfoByTime")
    public ModelAndView reposInfoByTime(QueryPara queryPara) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        initalModelView(modelAndView, user);
        List<RepositoryInfo> repositoryInfoList = reposService.findRepositoryInfoByTime(queryPara.getStartTime(), queryPara.getEndTime());
        modelAndView.addObject("repositoryInfoList", repositoryInfoList);
        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }

    private void initalModelView(ModelAndView modelAndView, User user) {
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<RepositoryStationInfo> repositoryStationInfoList = reposService.findReposStationInfoByReposNum(user.getUserComp());
        modelAndView.addObject("repositoryStationInfoList", repositoryStationInfoList);
        modelAndView.addObject("addReposInfo", new RepositoryInfo());
        modelAndView.addObject("reposInfoById", new RepositoryInfo());
        modelAndView.addObject("reposInfoByTime", new QueryPara());
    }
    /***************************************************repository transport Man*****************************************/
    /**
     * @Author:yangjun
     * @Description:
     * @Date: Created in 19:21 2018/6/4
     */
    @GetMapping("/repository/repoTransMan")
    public ModelAndView repoTransMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("addTransInfo", new TransportInfo());
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<TransStationInfo> transStationInfo = transporterService.findTransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);

        modelAndView.addObject("startStationNum", user.getUserComp());
        modelAndView.addObject("startStationName", user.getUserCompName());

        modelAndView.addObject("addTransInfo", new TransportInfo());
        modelAndView.addObject("transInfoById", new TransportInfo());
        modelAndView.addObject("transInfoByTime", new QueryPara());

        List<ComInfo> comInfoList = transporterService.findTransOrReposInfo();
        modelAndView.addObject("comInfoList", comInfoList);


        modelAndView.setViewName("repository/repoTransMan");
        return modelAndView;
    }

    @PostMapping("/repository/addTransInfo")
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
        addTransInfo.setProBatchNum(coopService.findProBatchNumByProNum(addTransInfo.getProNum()));
        addTransInfo.setRecordedTime(publicService.formatTime(addTransInfo.getRecordedTime()));
        transporterService.saveTransInfo(addTransInfo);

        modelAndView.addObject("addTransInfo", new TransportInfo());
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<TransStationInfo> transStationInfo = transporterService.findTransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);
        modelAndView.addObject("addTransInfo", new TransportInfo());
        modelAndView.addObject("transInfoById", new TransportInfo());
        modelAndView.addObject("transInfoByTime", new QueryPara());
        modelAndView.addObject("startStationNum", user.getUserComp());
        modelAndView.addObject("startStationName", user.getUserCompName());

        comInfoList = transporterService.findTransOrReposInfo();
        modelAndView.addObject("comInfoList", comInfoList);

        modelAndView.setViewName("repository/repoTransMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/transInfoById")
    public ModelAndView transInfoById(TransportInfo transInfoById) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<TransportInfo> transInfoResList = transporterService.findTransInfoByProNum(transInfoById.getProNum());
        modelAndView.addObject("transInfoResList", transInfoResList);
        modelAndView.addObject("addTransInfo", new TransportInfo());
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);

        List<TransStationInfo> transStationInfo = transporterService.findTransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);
        modelAndView.addObject("startStationNum", user.getUserComp());

        modelAndView.addObject("startStationName", user.getUserCompName());

        List<ComInfo> comInfoList = transporterService.findTransOrReposInfo();
        modelAndView.addObject("comInfoList", comInfoList);
        modelAndView.addObject("addTransInfo", new TransportInfo());
        modelAndView.addObject("transInfoById", new TransportInfo());
        modelAndView.addObject("transInfoByTime", new QueryPara());
        modelAndView.setViewName("repository/repoTransMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/transInfoByTime")
    public ModelAndView transInfoByTime(QueryPara queryPara) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<TransportInfo> transInfoResList = transporterService.findTransInfoByTime(queryPara.getStartTime(), queryPara.getEndTime());
        modelAndView.addObject("transInfoResList", transInfoResList);

        modelAndView.addObject("addTransInfo", new TransportInfo());
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<TransStationInfo> transStationInfo = transporterService.findTransStationInfo();
        modelAndView.addObject("transStationInfo", transStationInfo);

        modelAndView.addObject("startStationNum", user.getUserComp());
        modelAndView.addObject("startStationName", user.getUserCompName());

        List<ComInfo> comInfoList = transporterService.findTransOrReposInfo();
        modelAndView.addObject("comInfoList", comInfoList);

        modelAndView.addObject("addTransInfo", new TransportInfo());
        modelAndView.addObject("transInfoById", new TransportInfo());
        modelAndView.addObject("transInfoByTime", new QueryPara());
        modelAndView.setViewName("repository/repoTransMan");
        return modelAndView;
    }

}
