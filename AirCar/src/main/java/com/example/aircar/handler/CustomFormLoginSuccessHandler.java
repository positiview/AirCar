package com.example.aircar.handler;


import com.example.aircar.domain.MemberSecurityDTO;
import com.example.aircar.entity.Member;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@AllArgsConstructor
public class CustomFormLoginSuccessHandler implements AuthenticationSuccessHandler {
//    private PasswordEncoder passwordEncoder;
    private MemberService memberService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-------------------------------------------");
        log.info(authentication.getPrincipal());

        MemberSecurityDTO memberSecurityDTO =
                (MemberSecurityDTO) authentication.getPrincipal();
        HttpSession session = request.getSession();
        String email = memberSecurityDTO.getEmail();
        Member member = memberService.getUserInfo(email);
        session.setAttribute("memberInfo", member);

        log.info("일반 로그인!!!");
        response.sendRedirect("/");
        // 소셜 로그인이 아닐경우




    }
}





