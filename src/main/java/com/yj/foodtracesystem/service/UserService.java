package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.Role;
import com.yj.foodtracesystem.model.User;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:54 2018/4/18
 */
public interface UserService {
    User findUserByUserNum(String userNum);
    List<User> findByUserNumOrName(String num,String name,String compNum);
    List<User> findAllUser();
    List<User> findByTime(String startTime,String endTime);
    void saveUser(User user);
    List<Role> findAllRole();
}
