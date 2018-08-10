package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangjun on 2018/07/13.
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户管理", description = "用户管理")
public class UserController extends BaseController {

   @Autowired
    private UserRepository userRepository;

    /**
     * 获取用户列表
     * @return
     */
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/userList")
    public Map<String, Object> userList(){
        List<User> users = userRepository.findAll();
        logger.info("users: {}", users);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("users",users);
        return map;
    }

    @ApiOperation(value = "查询用户权限")
    @GetMapping("/authorityList")
    public List<String> authorityList(){
        logger.info("query user list:");
        List<String> authentication = getAuthentication();
        return authentication;
    }

}
