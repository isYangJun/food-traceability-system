package com.yj.foodtracesystem;

import com.yj.foodtracesystem.model.FiledInfo;
import com.yj.foodtracesystem.model.Role;
import com.yj.foodtracesystem.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesTest {

    @Autowired
    private UserService userService;


    @Test
    public void testFindFiledInfo() {
        List<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setRole("hh");
        role.setId(1);
        roles.add(role);
        Mockito.when(userService.findAllRole()).thenReturn(roles);

        List<Role> res=userService.findAllRole();
        Assert.assertTrue(res.size()==roles.size());

    }
}
