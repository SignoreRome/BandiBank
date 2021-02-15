package com.company.BandiBank.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {

    @GetMapping
    public String hello(){
        return "/helloPage";
    }
}
