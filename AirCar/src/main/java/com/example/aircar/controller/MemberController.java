package com.example.aircar.controller;


import com.example.aircar.service.CustomOAuth2UserDetailsService;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {


    private MemberService memberService;

    private CustomOAuth2UserDetailsService customOAuth2UserDetailsService;
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/license")
    public String driveLicense(){
        return "member/driveLicense";
    }

    @GetMapping("/carInfo")
    public void carInfo(){

    }

    /*@GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

        return "member/memberLoginForm";
    }*/


}
