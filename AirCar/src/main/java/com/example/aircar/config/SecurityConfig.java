package com.example.aircar.config;


import com.example.aircar.handler.CustomFormLoginSuccessHandler;


import com.example.aircar.handler.CustomSocialLoginSuccessHandler;
import com.example.aircar.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정을 작성
        http.formLogin()
                .loginPage("/login")
                /*.defaultSuccessUrl("/main")*/
                .successHandler(authenticationFormLoginSuccessHandler())
                .usernameParameter("id")
                .passwordParameter("pw")
                .failureUrl("/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/main")
                .invalidateHttpSession(true)
        ;

        http.oauth2Login()
                .loginPage("/login")
//                .defaultSuccessUrl("/main")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/main")
                .invalidateHttpSession(true)
        ;

// csrf 전송 끄기
       //http.csrf().disable();

    /*    permitAll() - 모든 사용자가 인증(로그인)없이 해당 경로에 접근할 수 있도록 설정
        anyRequest().authenticated() - mvcMatchers에서 설정해준 경로를 제외한
                                      나머지 경로들은 모두 인증을 요구하도록 설정
         */

       /* http.authorizeRequests()
            .mvcMatchers("/assets/**").permitAll()
            .mvcMatchers("/main", "/login", "/").permitAll()
            //.mvcMatchers("/board/**").
            .mvcMatchers("/admin/**").hasRole("ADMIN")

            .anyRequest().authenticated()
        ;*/

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder의 해시 함수를 이용해서 비밀번호 암호화
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationFormLoginSuccessHandler() {
        return new CustomFormLoginSuccessHandler();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomSocialLoginSuccessHandler(passwordEncoder(), memberService);
    }


}




