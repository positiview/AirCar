package com.example.aircar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {
    @GetMapping("/reserve")
    public String reserve(){
        return "/reserve/reserve";
    }
}
