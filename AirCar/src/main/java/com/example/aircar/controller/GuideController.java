package com.example.aircar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuideController {
    @GetMapping("/crm")
    public String crmForm(){
        return "/guide/crm";
    }

    @GetMapping("/instructions")
    public String instructions(){
        return "/guide/instructions";
    }

    @GetMapping("/short_renting")
    public String shosrt(){
        return "/guide/short_renting";
    }

    @GetMapping("/long_personal")
    public String personal(){
        return "/guide/long_personal";
    }

    @GetMapping("/discount")
    public String discount(){
        return "/guide/discount";
    }

    @GetMapping("/electric")
    public String electric(){
        return "/guide/electric";
    }

    @GetMapping("/insurance")
    public String insurance(){
        return "/guide/insurance";
    }

    @GetMapping("/long_corporation")
    public String corporation(){
        return "/guide/long_corporation";
    }

}
