package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.RepositoryInfo;
import com.yj.foodtracesystem.model.RepositoryStationInfo;
import com.yj.foodtracesystem.model.TempModel.QueryPara;
import com.yj.foodtracesystem.model.TransportInfo;
import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.service.ReposService;
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
 * @Date: Created in 21:54 2018/5/30
 */
@Controller
public class RepositoryController {

    @Autowired
    UserService userService;
    @Autowired
    ReposService reposService;

    @GetMapping("/repository/repositoryMan")
    public ModelAndView repositoryMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
       // List<RepositoryStationInfo> repositoryStationInfoList = reposService.findAllRepositoryStationInfo();
        List<RepositoryStationInfo> repositoryStationInfoList = reposService.findReposStationInfoByReposNum(user.getUserComp());
        modelAndView.addObject("repositoryStationInfoList", repositoryStationInfoList);
        modelAndView.addObject("addReposInfo", new RepositoryInfo());
        modelAndView.addObject("reposInfoById", new RepositoryInfo());
        modelAndView.addObject("reposInfoByTime", new QueryPara());
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
        reposService.saveRepositoryInfo(repositoryInfo);
        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<RepositoryStationInfo> repositoryStationInfoList =reposService.findReposStationInfoByReposNum(user.getUserComp());
        modelAndView.addObject("repositoryStationInfoList", repositoryStationInfoList);
        modelAndView.addObject("addReposInfo", new RepositoryInfo());
        modelAndView.addObject("reposInfoById", new RepositoryInfo());
        modelAndView.addObject("reposInfoByTime", new QueryPara());
        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/reposInfoById")
    public ModelAndView reposInfoById(RepositoryInfo repositoryInfo) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<RepositoryInfo> repositoryInfoList = reposService.findRepositoryInfoByProNum(repositoryInfo.getProNum());
        modelAndView.addObject("repositoryInfoList", repositoryInfoList);

        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<RepositoryStationInfo> repositoryStationInfoList = reposService.findReposStationInfoByReposNum(user.getUserComp());
        modelAndView.addObject("repositoryStationInfoList", repositoryStationInfoList);
        modelAndView.addObject("addReposInfo", new RepositoryInfo());
        modelAndView.addObject("reposInfoById", new RepositoryInfo());
        modelAndView.addObject("reposInfoByTime", new QueryPara());

        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }

    @PostMapping(value = "/repository/reposInfoByTime")
    public ModelAndView reposInfoByTime(QueryPara queryPara) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        List<RepositoryInfo> repositoryInfoList = reposService.findRepositoryInfoByTime(queryPara.getStartTime(),queryPara.getEndTime());
        modelAndView.addObject("repositoryInfoList", repositoryInfoList);

        List<TransportInfo> proNumNameList = reposService.findOwnedTransportInfo(user.getUserComp());
        modelAndView.addObject("proNumNameList", proNumNameList);
        List<RepositoryStationInfo> repositoryStationInfoList = reposService.findReposStationInfoByReposNum(user.getUserComp());
        modelAndView.addObject("repositoryStationInfoList", repositoryStationInfoList);
        modelAndView.addObject("addReposInfo", new RepositoryInfo());
        modelAndView.addObject("reposInfoById", new RepositoryInfo());
        modelAndView.addObject("reposInfoByTime", new QueryPara());

        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }
}
