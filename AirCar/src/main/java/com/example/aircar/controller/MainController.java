package com.example.aircar.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String homepage(){
        return "main/homepage";
    }

    @GetMapping("/index")
    public String slickPractice(){
        return "index";
    }
}
