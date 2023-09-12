package com.example.aircar.repository;

import com.example.aircar.entity.Reserve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReserveRepository extends JpaRepository<Reserve, UUID> {

    Reserve findByRno(UUID rno);

    @Query("select r from Reserve r where r.email = :email")
    List<Reserve> findByEmail(@Param("email") String email);

    @Query("select r from Reserve r where r.nickname like %:keyword% order by r.rno desc")
    Page<Reserve> getByNicknameLike(String keyword, Pageable pageable);

    @Query("select r from Reserve r where r.email like %:keyword% order by r.rno desc")
    Page<Reserve> getByemailLike(String keyword, Pageable pageable);
}
