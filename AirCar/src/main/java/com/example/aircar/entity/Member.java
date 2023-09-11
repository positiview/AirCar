package com.example.aircar.entity;

import com.example.aircar.constant.Role;
import com.example.aircar.domain.MemberFormDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @Column(name="mno")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;

    private String password;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;
   /* private String address;

    private String license;

    private String carInfo;*/

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean social;

    private String nickname;

    public static Member createMember(MemberFormDto memberFormDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDTO.getName());
        member.setEmail(memberFormDTO.getEmail());


        String password = passwordEncoder.encode(memberFormDTO.getPassword());

        member.setPassword(password);
        member.setRole(Role.USER);
        member.setSocial(false);  //false 이면 일반회원 true 이면 소셜 회원!

        return member;
    }

}
