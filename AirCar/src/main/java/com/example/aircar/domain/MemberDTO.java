package com.example.aircar.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {

    private Long mNo;
    private String memberPw;
    private String memberName;
    private String memberEmail;
    private LocalDateTime memberRegDate;

}
