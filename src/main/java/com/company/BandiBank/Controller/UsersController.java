package com.company.BandiBank.Controller;

import com.company.BandiBank.Service.ServiceImpl.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserServiceImp userServiceImp;

    @Autowired
    public UsersController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("users",userServiceImp.index());
        return "/index";
    }

}
