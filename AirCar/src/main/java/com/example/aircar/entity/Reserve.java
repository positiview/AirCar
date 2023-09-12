package com.example.aircar.entity;

import com.example.aircar.constant.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reserve")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reserve extends BaseEntity{

    @Id
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2" , strategy = "uuid2")
    private UUID rno;

    private String email;   // 예약한 사람 아이디
    private String nickname; //예약 한 사람
    private Long carNum;
    private String carName; //차이름
    private Date reserveDate;
    private LocalDateTime regDate;

    private Date startDate;
    private Date endDate;

}
