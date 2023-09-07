package com.example.aircar.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CounselingDTO {
    private long bno;
    private String counseling_email;
    private String counseling_type;
    private String counseling_stage;
    private String counseling_title;
    private String counseling_content;
    private LocalDateTime reg_time;

    private String answer;


}
