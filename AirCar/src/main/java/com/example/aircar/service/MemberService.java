package com.example.aircar.service;


import com.example.aircar.entity.Member;
import com.example.aircar.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class MemberService  {

    private MemberRepository memberRepository;

    public  Member saveMember(Member member){
        validateDuplicatedMember(member);

        return memberRepository.save(member);

    }

    private void validateDuplicatedMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        // .isPresent() - Optional 객체가 값을 가지고 있다면 true, 없다면 false
        if(findMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원 입니다.");
        }
    }

    public String getPassword(String email){
        Member result = memberRepository.findPasswordByEmail(email);



        return result.getPassword();
    }

    public Member getUserInfo(String email){
        Optional<Member> memberInfo  = memberRepository.findByEmail(email);

            if(memberInfo.isEmpty()){
                return null;
            }else{
                return memberInfo.get();
            }
    }


    public void updatePhone(String phone, String email){
        memberRepository.updatePhone(phone,email);
    }

    /*public String getNicknameByEmail(String email){
        Optional<Member> memberItem = memberRepository.findByEmail(email);
        log.info("memberItem : " + memberItem);
        if(memberItem.isEmpty()){
            throw new UsernameNotFoundException(email);
        }
        return memberItem.get().getNickname();
    }*/


   /* public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByEmail(email);

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

    public Page<Member> getMemberList(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }


    public Page<Member> getnicknameList(String keyword, Pageable pageable) {
        return memberRepository.getBynicknameLike(keyword, pageable);
    }

    public Page<Member> getemailList(String keyword, Pageable pageable) {
        return memberRepository.getByemailLike(keyword, pageable);
    }
}
