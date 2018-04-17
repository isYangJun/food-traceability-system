package com.yj.foodtraceabilitytystem.dao;



/**
 * @Author:YangJun
 * @Description:
 * @Date: Created in 22:40 2018/4/15
 */
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Integer> {
}
