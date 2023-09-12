package com.example.aircar.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class CarDTO {
    private long car_num;

    private String kind;
    private String color;
    private String brand;
    private String name;
    private int cost;
    private int year;
    private String options;
    private String fuel;
    private int people;
    private String area;
    private String detailarea;
    private String defect;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private int driverAge;
    private int driverCareer;

//    private Long fileNum;
//    private String brandName;
//    private String carName;
    private String carImg;
    private String brandImg;
    private Date startDate;
    private Date endDate;

    //private int
}
