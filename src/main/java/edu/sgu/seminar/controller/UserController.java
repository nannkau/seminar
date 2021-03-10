package edu.sgu.seminar.controller;

import edu.sgu.seminar.entity.User;
import edu.sgu.seminar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {
    final private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/register.html","/register"})
    public String register(Model model){
        User user= new User();
        model.addAttribute("user",user);
       return  "register";
    }
    @RequestMapping(value = {"/register.html","/register"},method = RequestMethod.POST)
    public String register(Model model, @Valid User user, BindingResult result){
        if(result.hasErrors())
        {
            return "register";
        }
        userService.save(user);
        return  "redirect:/login";
    }
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value="/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
