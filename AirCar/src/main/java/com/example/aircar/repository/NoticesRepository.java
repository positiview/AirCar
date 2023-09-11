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


    //Page<Notices> findByNotices_titleLikeOrderByBno(String keyword, Pageable pageable);
    @Query("select n from Notices n where n.notices_title like %:keyword% order by n.bno desc")
    Page<Notices> getByNotices_titleLike(String keyword, Pageable pageable);

    @Query("select n from Notices n where n.notices_content like %:keyword% order by n.bno desc")
    Page<Notices> getByNotices_contentLike(String keyword, Pageable pageable);


}
