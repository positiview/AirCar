package com.example.aircar.repository;

import com.example.aircar.entity.Notices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticesRepository extends JpaRepository<Notices, Long> {

    @Query("select b from Notices b where b.notices_title like %:title% order by b.bno desc")
    List<Notices> findByTitle(@Param("title") String title);

    Notices findByBno(Long bno);



}
