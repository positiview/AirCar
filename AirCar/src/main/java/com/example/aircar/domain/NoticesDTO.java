package com.example.aircar.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticesDTO {
    private long bno;
    private String notices_title;
    private String notices_category;
    private String notices_content;
    private LocalDateTime reg_time;
}
