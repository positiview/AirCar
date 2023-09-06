package com.example.aircar.service;

import com.example.aircar.domain.MemberSecurityDTO;
import com.example.aircar.entity.Member;
import com.example.aircar.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class MemberService  {

    private MemberRepository memberRepository;

    public  Member saveMember(Member member){
        validateDuplicatedMember(member);

        return memberRepository.save(member);

    }

    private void validateDuplicatedMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmailAndClientName(member.getEmail(), member.getClientName());
        // .isPresent() - Optional 객체가 값을 가지고 있다면 true, 없다면 false
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원 입니다.");
        }
    }

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmailAndClientName(email);

        if (!member.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        MemberSecurityDTO dto = new MemberSecurityDTO(
                member.get().getEmail(),
                member.get().getPassword(),
                member.get().isSocial(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_" + member.get().getRole()))
        );

        return dto;
    }*/
}
