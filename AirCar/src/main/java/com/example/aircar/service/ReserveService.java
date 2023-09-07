package com.example.aircar.service;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReserveService {

    private CarRepository carRepository;
    public List<Car> getCarList() {
        return carRepository.findAll();
    }
}
