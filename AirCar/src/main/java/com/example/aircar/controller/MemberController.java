package com.example.aircar.controller;


import com.example.aircar.domain.LoginDTO;
import com.example.aircar.domain.MemberSecurityDTO;
import com.example.aircar.entity.Member;
import com.example.aircar.service.CustomOAuth2UserDetailsService;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /*@PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO, Model model){
        String userId = loginDTO.getId(); // userId는 email이다
        // 사용자가 입력한 비밀번호
        String userPassword = loginDTO.getPw();

        // 데이터베이스에서 가져온 저장된 해시된 비밀번호
        Member member =  memberService.getUserInfo(userId);
        String storedHashedPassword = member.getPassword();
        boolean passwordMatches = passwordEncoder.matches(userPassword, storedHashedPassword);

        if(passwordMatches){

            if(member.getRole().toString().equals("USER")){
                return "redirect:/main/homepage";
            }else {
                return "redirect:/admin/main";
            }
        }else{
            *//*model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");*//*

            return "redirect:/login";
        }
    }*/
    @PostMapping("/login")
    public String login(String id, String pw, Model model){


        // 데이터베이스에서 가져온 저장된 해시된 비밀번호
        Member member =  memberService.getUserInfo(id);
        String storedHashedPassword = member.getPassword();
        boolean passwordMatches = passwordEncoder.matches(pw, storedHashedPassword);

        if(passwordMatches){

            if(member.getRole().toString().equals("USER")){
                model.addAttribute("member",member);
                //return "/main/homepage";
                return "redirect:/main";
            }else {
                return "redirect:/admin/main";
            }
        }else{
            model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

            return "redirect:/login";
        }
    }
    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

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

    @GetMapping("/adminTest")
    public String admin(){
        return "member/AdminLogin";
    }

    /*@GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

        return "member/memberLoginForm";
    }*/


    @PostMapping("/passwordCheck")
    public ResponseEntity<String> passwordCheck(Model model, @AuthenticationPrincipal MemberSecurityDTO member, String password){

        String email = member.getEmail();
        String userInputPassword = password;
        String storedHashedPassword = memberService.getPassword(email);

        boolean passwordMatches = passwordEncoder.matches(userInputPassword, storedHashedPassword);

        /*if(passwordMatches){
            return "/myinfo";
        }else{
            model.addAttribute("pwChkErrMsg", "비밀번호를 잘못 입력하셨습니다.");
            return "redirect:/myPage";
        }*/
        return new ResponseEntity<>("correct", HttpStatus.OK);
    }

}
