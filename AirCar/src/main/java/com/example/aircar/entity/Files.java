package com.example.aircar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileNum;

    private String brandName;

    @Column(unique = true)
    private String carName;

    private String carImg;
    private String brandImg;


}
