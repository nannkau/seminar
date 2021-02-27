package edu.sgu.seminar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = {"","/index","index.html"})
    public String index(){
        return "home/index";
    }
}
