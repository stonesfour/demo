package com.caicloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

    @RequestMapping(value = "/")
    public String index(){
        return "views/index";
    }

    @RequestMapping(value = "/welcome")

    public String home(){
        return "views/welcome";
    }
}