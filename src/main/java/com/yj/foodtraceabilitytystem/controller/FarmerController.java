package com.yj.foodtraceabilitytystem.controller;

import com.yj.foodtraceabilitytystem.dao.User;
import com.yj.foodtraceabilitytystem.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:YangJun
 * @Description:
 * @Date: Created in 18:33 2018/4/15
 */
@RestController
public class FarmerController {
   @Autowired
    private UserRepository userRepository;

    /* *//*
     * 查询person信息
     * */
    @GetMapping("/perquery")
    public User personquery(@RequestParam("id") Integer id){
        User p=userRepository.findById(id).orElse(null);
        return p;
    }


}
