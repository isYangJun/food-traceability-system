package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 21:54 2018/5/30
 */
@Controller
public class RepositoryController {
    @Autowired
    UserService userService;


    @GetMapping("/repository/repositoryMan")
    public ModelAndView repositoryMan() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserCompName() + ": " + user.getName() + " (" + user.getUserNum() + ")");
        modelAndView.setViewName("repository/repositoryMan");
        return modelAndView;
    }


}
