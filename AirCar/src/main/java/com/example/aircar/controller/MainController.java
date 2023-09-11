package com.example.aircar.controller;

import com.example.aircar.domain.MemberSecurityDTO;

import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Log4j2
public class MainController {

    @GetMapping("/main")
    public String homepage(Model model, @AuthenticationPrincipal MemberSecurityDTO mDTO,
                           HttpServletRequest request) {

        String nickname = "";
        HttpSession session = request.getSession();
        if (mDTO != null) {

            nickname = mDTO.getNickname();
            log.info("nickname : " + nickname);
            session.setAttribute("memberInfo", mDTO);
        }

        log.info("member 정보 : "+ mDTO);

        model.addAttribute("nickname", nickname);



        session.setAttribute("nickname", nickname);

        return "main/homepage";
    }

    @GetMapping("/index")
    public String slickPractice(){
        return "index";
    }
}
