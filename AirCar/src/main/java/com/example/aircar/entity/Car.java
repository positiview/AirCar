package com.example.aircar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carinfo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car extends BaseEntity{
    @Id
    @Column(name = "carNum")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carNum;

    private String kind;
    private String color;
    private String brand;
    private String name;
    private Integer cost;
    private Integer year;
    private String options;
    private String fuel;
    private Integer people;
    private String area;
    private String detailarea;
    private String defect;
    private String content;
    private Integer driverAge;
    private Integer driverCareer;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Date startDate;
    private Date endDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private  member_num;



}
