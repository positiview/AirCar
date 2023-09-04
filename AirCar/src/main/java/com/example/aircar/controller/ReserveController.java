package com.example.aircar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {
    @GetMapping("/reserve")
    public String reserve(){
        return "/reserve/reserve";
    }

    @GetMapping("/reserve_confirm")
    public String reserveCofirm(){
        return "/reserve/reserve_confirm";
    }

    @GetMapping("/map")
    public String maps(){
        return "/reserve/map";
    }

    @GetMapping("/register_car")
    public String register(){
        return "/reserve/register_car";
    }

    @GetMapping("/car_detail")
    public String detail() {
        return "/reserve/car_detail";
    }
}
