package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:53 2018/4/18
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserNum(String userNum);
}
