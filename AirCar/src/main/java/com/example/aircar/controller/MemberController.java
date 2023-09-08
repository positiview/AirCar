package com.example.aircar.controller;


import com.example.aircar.service.CustomOAuth2UserDetailsService;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class MemberController {


    private MemberService memberService;

    private CustomOAuth2UserDetailsService customOAuth2UserDetailsService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String memberLogin(){
        return "member/memberLogin";
    }

    @PostMapping("/login")
    public String login(String id, String password){
        // 사용자가 입력한 비밀번호
        String userInputPassword = "password";

        // 데이터베이스에서 가져온 저장된 해시된 비밀번호
        String storedHashedPassword = "$2a$10$7AxNWsnM2UnYPlc3D9.kmuBRx15anSXcYWTM08M7g0TczrlwbNy4W";

        // PasswordEncoder를 생성합니다.
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 사용자가 입력한 비밀번호를 동일한 방식으로 해싱하여 저장된 해시된 비밀번호와 비교합니다.
        boolean passwordMatches = passwordEncoder.matches(userInputPassword, storedHashedPassword);

        return "main/homepage";
    }
    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

        return "member/memberLoginForm";
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

    @GetMapping("/adminTest")
    public String admin(){
        return "member/AdminLogin";
    }

    /*@GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

        return "member/memberLoginForm";
    }*/


}
