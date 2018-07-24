package com.yj.foodtracesystem.service;

import com.yj.foodtracesystem.model.Role;
import com.yj.foodtracesystem.model.TempModel.OperationHisResult;
import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.repository.RoleRepository;
import com.yj.foodtracesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:55 2018/4/18
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private PublicService publicService;
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findUserByUserNum(String userNum) {
        return userRepository.findByUserNum(userNum);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
       /* Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));*/
        userRepository.save(user);
    }

    @Override
    public List<User> findByUserNumOrName(String name, String num, String compNum) {
        String sql = "SELECT tbl_user.name,user_num,id_num,sex,reg_time,active FROM tbl_user WHERE (tbl_user.name='"+name+"' OR user_num='"+num+"')" +
                "AND user_comp='"+compNum+"' ";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> list = nativeQuery.getResultList();
        List<User> userList = publicService.convertToType(list,User.class.getName());
        return userList;
    }

    @Override
    public List<User> findByTime(String startTime, String endTime) {
        return userRepository.findByRegTimeBetween(startTime,endTime);
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }
}
