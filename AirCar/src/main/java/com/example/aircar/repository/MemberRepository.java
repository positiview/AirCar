package com.example.aircar.repository;

import com.example.aircar.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.contactEmail = :contactEmail WHERE m.email = :email")
    void updateContactEmail(String contactEmail, String email);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.nickname = :nickname WHERE m.email = :email")
    void updateNickname(String nickname, String email);


    void deleteByEmail(String email);

    Member findPasswordByEmail(String email);

    Member findByMno(Long mno);


    @Query("select m from Member m where m.nickname like %:keyword% order by m.mno desc")
    Page<Member> getBynicknameLike(String keyword, Pageable pageable);

    @Query("select m from Member m where m.email like %:keyword% order by m.mno desc")
    Page<Member> getByemailLike(String keyword, Pageable pageable);


}