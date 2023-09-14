package com.example.aircar.controller;

import com.example.aircar.constant.Role;
import com.example.aircar.domain.MemberSecurityDTO;

import com.example.aircar.entity.Member;
import com.example.aircar.repository.MemberRepository;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Log4j2
@AllArgsConstructor
public class MainController {

    MemberService memberService;

    @GetMapping({"/main","/"})
    public String homepage(@AuthenticationPrincipal MemberSecurityDTO mDTO,
                           HttpServletRequest request) {


        HttpSession session = request.getSession();
        if (mDTO != null) {

            String email = mDTO.getEmail();
            log.info("email 값 : " + email);
            Member member = memberService.getUserInfo(email);
            session.setAttribute("memberInfo", member);

        }

        log.info("member 정보 : "+ mDTO);




        return "main/homepage";
    }

    @GetMapping("/index")
    public String slickPractice(){
        return "index";
    }

    @GetMapping("/bookoverseas")
    public String bookoverseas(){
        return "etc/bookoverseas";
    }


    @GetMapping("/direct")
    public String direct(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Member mem = (Member) session.getAttribute("memberInfo");

        if (mem == null) {

            return "redirect:/login";
        } else {
            Role role = mem.getRole();
            if (Role.ADMIN.equals(role)) {
                return "redirect:/admin/main";
            } else {
                return "redirect:/mypage";
            }
        }
    }
}
