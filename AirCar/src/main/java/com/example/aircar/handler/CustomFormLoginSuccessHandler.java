package com.example.aircar.handler;


import com.example.aircar.domain.MemberSecurityDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@AllArgsConstructor
public class CustomFormLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("-------------------------------------------");
        log.info(authentication.getPrincipal());

        MemberSecurityDTO memberSecurityDTO =
                (MemberSecurityDTO) authentication.getPrincipal();

        // 소셜 로그인이 아닐경우
        if (!memberSecurityDTO.isSocial()) {
            log.info("일반 로그인!!!");
            response.sendRedirect("/main");

        } else {
            log.info("소셜 로그인!!! 일반 로그인 불가");
            response.sendRedirect("/members/logout");
        }
    }
}





