package com.example.aircar.repository;

import com.example.aircar.entity.Counseling;
import com.example.aircar.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {



    Counseling findByBno(Long bno);



}