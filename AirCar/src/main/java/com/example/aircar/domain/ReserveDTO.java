package com.example.aircar.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReserveDTO {
    private UUID rno;

    private String email;
    private Long carNum;
    private String carName;
    private Date reserveDate;
    private String nickname;
    private LocalDateTime regDate;

    private String brandImg;
    private String carImg;

    private Date startDate;
    private Date endDate;

}
