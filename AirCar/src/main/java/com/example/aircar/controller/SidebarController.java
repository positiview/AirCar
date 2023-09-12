package com.example.aircar.controller;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class SidebarController {

    CarService carService;
    @PostMapping("/searchCar")
    public String searchCar(String startdate, String enddate, Model model){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = formatter.parse(startdate);
            Date endDate = formatter.parse(enddate);
            List<CarDTO> carsDTO = carService.searchCar(startDate, endDate);
            model.addAttribute("carInfo", carsDTO);
        } catch (ParseException e) {
            e.printStackTrace(); // 날짜 파싱 오류 처리
            return null;
        }

        return "/reserve/reserve";
    }



}
