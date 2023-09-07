package com.example.aircar.repository;

import com.example.aircar.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Long> {

//    List<Files> findByCarName(String carName);
//    List<Files> findDistinctByBrandName();
    @Query("select DISTINCT brandName from Files")
    List<String> findDistinctByBrandName();

//    @Query("select carImg from Files where carName =")
    Files findByCarImg(String carName);
    Files findByBrandImg(String carName);
    List<Files> findByBrandName(String brandName);

//    List<Files> findByCarNum(Long carNum);

    List<Files> findByFileNum(Long carNum);

    Files findByCarName(String carName);

//    List<Files> findByCarImg(String carImg);
}
