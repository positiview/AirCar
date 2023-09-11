package com.example.aircar.repository;

import com.example.aircar.entity.Member;
import com.example.aircar.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.password = :password WHERE m.email = :email")
    void updatePassword(String password, String email);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.phone = :phone WHERE m.email = :email")
    void updatePhone(String phone, String email);


    Member findPasswordByEmail(String email);

    Member findByMno(Long mno);


}