package com.example.aircar.repository;

import com.example.aircar.entity.Counseling;
import com.example.aircar.entity.Notices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CounselingRepository extends JpaRepository<Counseling, Long> {


//    @Query("select c from Counseling c where c.counseling_title like %:keyword% order by c.bno desc")
//    List<Counseling> findByTitle(@Param("title") String title);

    Counseling findByBno(Long bno);

    @Query("select c from Counseling c where c.counseling_title like %:keyword% order by c.bno desc")
    Page<Counseling> getByCounseling_titleLike(String keyword, Pageable pageable);

    @Query("select c from Counseling c where c.counseling_content like %:keyword% order by c.bno desc")
    Page<Counseling> getByCounseling_contentLike(String keyword, Pageable pageable);

}