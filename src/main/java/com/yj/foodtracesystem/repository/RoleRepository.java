package com.yj.foodtracesystem.repository;

import com.yj.foodtracesystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:51 2018/4/18
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
