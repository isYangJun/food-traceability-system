package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.Role;
import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.repository.RoleRepository;
import com.yj.foodtracesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:55 2018/4/18
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByUserNum(String userNum) {
        return userRepository.findByUserNum(userNum);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
}
