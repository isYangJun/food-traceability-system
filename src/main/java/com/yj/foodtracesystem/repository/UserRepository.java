package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:53 2018/4/18
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNum(String userNum);
    @Override
    List<User> findAll();
    List<User> findByRegTimeBetween(String startTime,String endTime);
    List<User> findByUserCompAndUserRole(String userComp,int userRole);
}
