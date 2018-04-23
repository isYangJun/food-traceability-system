package com.yj.foodtracesystem.controller;

import com.yj.foodtracesystem.model.Role;
import com.yj.foodtracesystem.model.User;
import com.yj.foodtracesystem.repository.UserRepository;
import com.yj.foodtracesystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:42 2018/4/18
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUserNum(user.getUserNum());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userNum", "error.user",
                            "There is already a user registered with the userNum provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "user has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserNum(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getUserNum() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    @RequestMapping(value = "/admin/qrcode", method = RequestMethod.GET)
    public String toQrcode() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "/admin/qrcode";
    }

    @RequestMapping(value = "/farmer/home", method = RequestMethod.GET)
    public String toUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "/farmer/home";
    }
    @RequestMapping(value = "/header", method = RequestMethod.GET)
    public String toHeader() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "header";
    }
}
