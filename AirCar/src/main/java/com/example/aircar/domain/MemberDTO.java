package com.example.aircar.domain;

import com.example.aircar.constant.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class MemberDTO {

    private Long mno;
    private String password;
    private String name;
    private String email;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
    private boolean social;

    private String nickname;
    private String createBy;
    private String modifyBy;
    private Role role;


}
