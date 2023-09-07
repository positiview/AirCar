package com.example.aircar.entity;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="counseling")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Counseling {
    @Id
    @Column(name="bno")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bno;

    private String counseling_email;

    private String counseling_type;

    private String counseling_stage;

    private String counseling_title;

    @Column(length = 5000)
    private String counseling_content;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(length = 5000)
    private String answer;
}
