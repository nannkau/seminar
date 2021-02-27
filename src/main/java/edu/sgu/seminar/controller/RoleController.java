package edu.sgu.seminar.controller;

import edu.sgu.seminar.entity.Role;
import edu.sgu.seminar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class RoleController {
    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/role/index.html")
    public String index(Model model){
        model.addAttribute("roles",roleService.findAll());
        return "role/index";
    }

    @RequestMapping(value = "/role/add.html")
    public String add(Model model){
        Role role= new Role();
        model.addAttribute("role",role);
        return "role/add";
    }
    @RequestMapping(value = "/role/add.html",method = RequestMethod.POST)
    public String add(@Valid Role role, BindingResult result){
        if (result.hasErrors()) {
            return "role/add";
        }
        else {
            roleService.save(role);
        }
        return "redirect:/role/index.html";
    }
    @RequestMapping(value = "/role/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Role role=roleService.findById(id);
        model.addAttribute("role",role);
        return "role/edit";
    }
    @RequestMapping(value = "/role/delete/{id}")
    public String delete(Model model,@PathVariable("id") Integer id){
        roleService.deleteById(id);
        return "redirect:/role/index.html";
    }
}
