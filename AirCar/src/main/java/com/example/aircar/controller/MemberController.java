package com.example.aircar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String memberLogin(){
        return "member/memberLogin";
    }

    @GetMapping("/signUpAgree")
    public String signUpAgree(){
        return "member/signUpAgreeChk";
    }

    @GetMapping("/mypage")
    public String mypage(){
        return "member/myPage";
    }

    @GetMapping("/myinfo")
    public String myinfo(){return "member/myInfo";}
}
