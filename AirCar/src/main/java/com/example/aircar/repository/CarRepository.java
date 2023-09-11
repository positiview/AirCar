package com.example.aircar.repository;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

//    Car findByModel(String model);

//    Car findByCarName(String carName);
//
//    @Query("select * from files f inner join carinfo c on f.car_name = c.name where c.name = f.car_name")
//    List<CarDTO> findByCarName();

//    @Query("SELECT f, c FROM Files f JOIN Car c ON f.carName = c.name")
    @Query("SELECT f FROM Files f JOIN Car c ON f.carName = c.name")
    List<CarDTO> findByCarInfo();


    Car findByCarNum(Long carNum);


    @Query("select c from Car c where c.brand like %:keyword% order by c.carNum desc")
    Page<Car> getBybrandLike(String keyword, Pageable pageable);

    @Query("select c from Car c where c.name like %:keyword% order by c.carNum desc")
    Page<Car> getBynameLike(String keyword, Pageable pageable);


}
