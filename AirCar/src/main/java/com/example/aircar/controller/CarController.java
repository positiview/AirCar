package com.example.aircar.controller;

import com.example.aircar.entity.Car;
import com.example.aircar.entity.Files;
import com.example.aircar.repository.CarRepository;
import com.example.aircar.repository.FilesRepository;
import com.example.aircar.service.CarService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final CarRepository carRepository;
    private final FilesRepository filesRepository;


    @GetMapping("/getCarBrands")
    private List<String> getCarBrands(){
        //데이터 베이스에서 차량 브랜드 목록을 가지고 오는 서비스 호출
        return carService.getAllCarBrand();

    }

    @GetMapping("/getCarModels")
    private List<Files> getCarModel(String brandName) {
        return carService.getAllCarModel(brandName);
    }

//    @GetMapping("/getCarImage")
//    public String getCarImage(@RequestParam("carImg") String carImg) {
//        // 차량 모델에 해당하는 이미지 URL을 가져오는 서비스 호출
//        return carService.getCarImageByModel(carImg);
//    }



}
