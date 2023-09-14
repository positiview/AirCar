package com.example.aircar.controller;



import com.example.aircar.domain.MemberFormDto;
import com.example.aircar.domain.MemberSecurityDTO;
import com.example.aircar.domain.ModifyDTO;
import com.example.aircar.entity.Member;
import com.example.aircar.service.CustomOAuth2UserDetailsService;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import javax.validation.Valid;


@Controller
@Log4j2
@AllArgsConstructor
@SessionAttributes("memberInfo")
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
    public String login(String id, String pw, Model model,HttpServletRequest request){


        // 데이터베이스에서 가져온 저장된 해시된 비밀번호
        Member member =  memberService.getUserInfo(id);
        String storedHashedPassword = member.getPassword();
        boolean passwordMatches = passwordEncoder.matches(pw, storedHashedPassword);
        HttpSession session = request.getSession();
        if(passwordMatches){

            if(member.getRole().toString().equals("USER")){
                /*model.addAttribute("member",member);*/
                if(session != null){
                    session.setAttribute("memberInfo", member);
                }

                return "main/homepage";
            }else {
                return "redirect:/admin/main";
            }
        }else{
            model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");

            return "redirect:/login";
        }
    }
    @GetMapping("/join")
    public String memberJoin(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "/member/memberJoin";
    }
    @PostMapping("/join")
    public String join(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            return "/member/memberJoin";
        }

        try{
            Member member =Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);

        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/member/memberJoin";
        }
        return "redirect:/login";
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
    public String mypage(Model model, @ModelAttribute("nickname") String nickname){


        model.addAttribute("nickname", nickname);
        return "member/myPage";
    }

    @GetMapping("/myInfo")
    public String myinfo(@AuthenticationPrincipal MemberSecurityDTO mDTO, Model model){
        String email = mDTO.getEmail();
        ;
        model.addAttribute("yourInfo",memberService.getUserInfo(email));
        return "member/myInfo";}


    @GetMapping("/myreserve")
    public String myReserve(){
        return "etc/myreserve";
    }


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
    @ResponseBody
    public ResponseEntity<String> passwordCheck(Model model, @RequestBody String password, @ModelAttribute("memberInfo") Member member){

        String email = member.getEmail();
        String userInputPassword = password.replace("=","");
        Member member2 = memberService.getUserInfo(email);
        if(member2 == null){
            model.addAttribute("pwChkErrMsg", "비밀번호를 잘못 입력하셨습니다.");
            /*return "redirect:/myPage";*/
            return new ResponseEntity<>("false", HttpStatus.OK);
        }
        String storedHashedPassword = member2.getPassword();

        boolean passwordMatches = passwordEncoder.matches(userInputPassword, storedHashedPassword);

        if(passwordMatches){
            return new ResponseEntity<>("correct", HttpStatus.OK);
        }else{
            model.addAttribute("pwChkErrMsg", "비밀번호를 잘못 입력하셨습니다.");
            /*return "redirect:/myPage";*/
            return new ResponseEntity<>("false", HttpStatus.OK);
        }

    }

    @PostMapping("/updateMember")
    @ResponseBody
    public ResponseEntity<String> updatePhone(@RequestBody ModifyDTO modifyDTO, @ModelAttribute("memberInfo") Member member){
        String access = modifyDTO.getAccess().replace("modify","");
        String value = modifyDTO.getValue();
        log.info("access 값 : "+access);
        log.info("value 값 : "+value);
        String email = member.getEmail();
        switch (access){
            case "Phone":
                memberService.updatePhone(value,email);
                break;
            case "Email":
                memberService.updateContactEmail(value,email);
                break;
            case "Nickname":
                memberService.updateNickname(value,email);
                break;
        }



        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @PostMapping("/deleteMember")
    @ResponseBody
    public ResponseEntity<String> deleteMember(HttpServletRequest request, Model model, @RequestBody String password, @ModelAttribute("memberInfo") Member member){

        String email = member.getEmail();
        String userInputPassword = password.replace("=","");
        Member member2 = memberService.getUserInfo(email);
        if(member2 == null){
            model.addAttribute("pwChkErrMsg", "비밀번호를 잘못 입력하셨습니다.");
            /*return "redirect:/myPage";*/
            return new ResponseEntity<>("false", HttpStatus.OK);
        }
        String storedHashedPassword = member.getPassword();

        boolean passwordMatches = passwordEncoder.matches(userInputPassword, storedHashedPassword);

        if(passwordMatches){
            memberService.deleteUser(email);
            HttpSession session = request.getSession(false);

            if(session != null) {
                session.invalidate();
            }
            return new ResponseEntity<>("correct", HttpStatus.OK);
        }else{
            model.addAttribute("pwChkErrMsg", "비밀번호를 잘못 입력하셨습니다.");
            /*return "redirect:/myPage";*/
            return new ResponseEntity<>("false", HttpStatus.OK);
        }

    }
}
