package com.yj.foodtraceabilitytystem.controller;

import com.yj.foodtraceabilitytystem.dao.User;
import com.yj.foodtraceabilitytystem.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Author:YangJun e4585cc4-6e72-40c0-a5a6-f3458e9d744b
* @Description: user login control
* @Date: Created in 15:50 2018/4/15
*/
@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/farmer")
    public String farmer(){
        return "farmer";
    }
    @RequestMapping("/cooperator")
    public String cooperator(){
        return "cooperator";
    }
    @GetMapping(value="/inde")
    public List<User> index(){
        return userRepository.findAll();
    }
    @GetMapping("/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登陆失败，用户名或者密码错误！");
        return "login";
    }




}
