package com.example.aircar.controller;

import com.example.aircar.domain.ReserveDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.repository.CarRepository;
import com.example.aircar.service.CarService;
import com.example.aircar.service.ReserveService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class PayController {

    @Autowired
    private ReserveService reserveService;
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;


    @PostMapping("/reserveInfo")
    public String reserveInsert(@RequestBody ReserveDTO reserveDTO, HttpSession session, RedirectAttributes rttr) {
        reserveService.saveReserve(reserveDTO,session,rttr);


        return "redirect:/reserve"; // 뷰 이름을 반환하지 않습니다.
    }

}
