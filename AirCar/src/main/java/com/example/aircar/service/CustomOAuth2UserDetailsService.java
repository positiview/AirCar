package com.example.aircar.service;


import com.example.aircar.constant.Role;
import com.example.aircar.domain.MemberSecurityDTO;
import com.example.aircar.entity.Member;
import com.example.aircar.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("oauth2 user....................");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("clientName: " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();
        paramMap.forEach((k, v) -> {
            log.info("------------------------");
            log.info(k + " : " + v);
        });
        String[] userinfo = null;
        String email = null;
        String nickname = null;

        if (clientName.equals("Google")) {  // 구글을 이용하는 경우
            userinfo = getGoogleEmail(paramMap);
        } else if (clientName.equals("naver")) {
            userinfo = getNaverEmail(paramMap);
        } else if (clientName.equals(("kakao"))){
            userinfo = getKaKaoEmail(paramMap);
        }
        email = userinfo[0];
        nickname = userinfo[1];
        log.info("E-mail : " + email);
        log.info("Nickname : " + nickname);


        return generateDTO(email, nickname, paramMap);
    }



    private String[] getNaverEmail(Map<String, Object> paramMap) {
        log.info("Naver--------------------");

        Map<String, Object> resultMap = (Map<String, Object>) paramMap.get("response");
        String email = (String) resultMap.get("email");
        String nickname = (String) resultMap.get("name");

        log.info("email : " + email);
        log.info("nickname : " + nickname);
        String[] userinfo = {email, nickname};

        return userinfo;
    }
    private String[] getKaKaoEmail(Map<String, Object> paramMap) {
        log.info("Kakao--------------------");

        Object value = paramMap.get("kakao_account");
        log.info(value);
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");
        LinkedHashMap profileMap = (LinkedHashMap) accountMap.get("profile");
        String nickname = (String) profileMap.get("nickname");
        log.info("email : " + email);
        log.info("nickname : " + nickname);
        String[] userinfo = {email, nickname};

        return userinfo;
    }

    private String[] getGoogleEmail(Map<String, Object> paramMap) {
        log.info("Google-------------------");

        String email = (String) paramMap.get("email");
        String nickname = (String) paramMap.get("given_name");
        log.info("email : " + email);
        log.info("nickname : " + nickname);
        String[] userinfo = {email, nickname};

        return userinfo;
    }

    private MemberSecurityDTO generateDTO(String email, String nickname, Map<String, Object> paramMap) {
        Optional<Member> result = memberRepository.findByEmail(email);
        String phone = "";
        // DB에 해당 이메일의 사용자가 없다면 자동으로 회원 가입 처리
        if (result.isEmpty()) {
            // 회원 추가
            // id = 이메일 주소 / 패스워드는 1111
            Member member = Member.builder()
                    .name(email)
                    .password(passwordEncoder.encode("1111"))
                    .email(email)
                    .social(true)
                    .phone(phone)
                    .nickname(nickname)
                    .role(Role.USER)
                    .build();

            // DB에 회원 정보 저장(회원 가입 처리)
            memberRepository.save(member);

            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(email,
                    "1111", email, true, nickname, phone,
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            memberSecurityDTO.setAttr(paramMap);

            return memberSecurityDTO;
        } else {    // 이미 가입된 회원은 기존 정보를 반환
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    member.getName(), member.getPassword(), member.getEmail(),
                    member.isSocial(), member.getNickname(), member.getPhone(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_" + member.getRole())));

            return memberSecurityDTO;
        }

    }

    public void updatePassword(String password, String email) {
        log.info("update Password---------------------");
        log.info("password : "+ password);
        memberRepository.updatePassword(password, email);
    }


//    private Optional<Member> saveSocialMember(String email) {
//        Optional<Member> result = memberRepository.findByEmail(email);
//
//        // 기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만
//        if (result != null) {
//            return result;
//        }
//
//        // 동일한 이메일이 없다면 member 추가
//        // 이름 - email, 패스워드 1111
//        MemberFormDto memberFormDto = new MemberFormDto();
//        memberFormDto.setEmail(email);
//        memberFormDto.setName(email);
//        memberFormDto.setPassword(passwordEncoder.encode("1111"));
//        Member member = Member.createMember(memberFormDto, passwordEncoder);
//
//        memberRepository.save(member);
//
//        return member;
//    }
}
