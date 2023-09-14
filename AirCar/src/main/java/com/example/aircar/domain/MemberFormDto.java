package com.example.aircar.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다.")
  //  @Length(min=8, max = 16, message = "비밀번호는 8자이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "번호는 필수 입력 값입니다.")
    private String phone;


}

/*
* @NotEmpty - NULL 체크 및 문자열의 경우 길이 0인지 검사
* @NotBlank - NULL 체크 및 문자열의 경우 0 및 빈 문자열(" ") 검사
* @Length(min=, max=) - 최소, 최대 길이 검사
* @Email - 이메일 형식인지 검사
* @Max(숫자) -
* @Min(숫자) -`1
* @Null
* @Notnull
*/

