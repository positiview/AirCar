package com.example.aircar.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConsultDTO {
    private Long consult_num;
    private String user_id;
    private String user_pw;
    private String consult_category;
    private String consult_title;
    private String consult_content;
    private LocalDateTime reg_time;
}
