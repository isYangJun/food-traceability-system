package com.yj.foodtraceabilitytystem.controller;

import com.yj.foodtraceabilitytystem.dao.User;
import com.yj.foodtraceabilitytystem.dao.UserRepository;
import com.yj.foodtraceabilitytystem.dao.UserType;
import com.yj.foodtraceabilitytystem.dao.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;

    /* *//*
     * 查询person信息
     * */
    @GetMapping("/userquery")
    public User userquery(@RequestParam("id") Integer id){
        User p=userRepository.findById(id).orElse(null);
        return p;
    }
    @GetMapping("/usertypequery")
    public UserType personquery(@RequestParam("userRoleId") Integer userRoleId){
        UserType p=userTypeRepository.findById(userRoleId).orElse(null);
        return p;
    }
}
