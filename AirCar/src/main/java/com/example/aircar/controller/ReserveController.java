package com.example.aircar.controller;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.entity.Files;
import com.example.aircar.repository.CarRepository;
import com.example.aircar.repository.FilesRepository;
import com.example.aircar.repository.ReserveRepository;
import com.example.aircar.service.CarService;
import com.example.aircar.service.ReserveService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
@Log4j2
public class ReserveController {

    private  CarRepository carRepository;
    private FilesRepository filesRepository;
    private CarService carService;
    private ReserveService reserveService;

//    @GetMapping("/reserve")
//    public String reserve(){
//        return "/reserve/reserve";
//    }

    @GetMapping("/reserve_confirm")
    public String reserveCofirm(){
        return "/reserve/reserve_confirm";
    }

    @GetMapping("/map")
    public String maps(){
        return "/reserve/map";
    }

    @GetMapping("/register_car")
    public String register(){
        return "/reserve/register_car";
    }

    @PostMapping("/register_car")
    public String registerCar(CarDTO carDTO) {
        carService.saveCar(carDTO);
        return "redirect:/reserve";
    }

//    @GetMapping("/car_detail")
//    public String detail() {
//        return "/reserve/car_detail";
//    }

    @GetMapping("/car_detail")
    public String car_detail(Long carNum, Model model){
        Car car = carRepository.findByCarNum(carNum);
//        List<Files> files = filesRepository.findByFileNum(carNum);

        model.addAttribute("car" ,car);
//        model.addAttribute("files" ,files);
        model.addAttribute("carinfo",carService.getCarInfo());
        log.info(carService.getCarInfo());
        return "reserve/car_detail";
    }

    @GetMapping("/reserve_first")
    public String first() {
        return "/reserve/reserve_first";
    }

    @GetMapping("/upload")
    public String upload(){

        return "/reserve/upload";
    }

    @GetMapping("/reserve")
    public String reserve(Model model){
//        List<Car> carList = carRepository.findAll();
//        model.addAttribute("car" , carList);
//        List<CarDTO> carDTOList = carService.getCarInfo();

//        model.addAttribute("carList",carService.getCarList());
        model.addAttribute("carInfo",carService.getCarInfo());

        return "reserve/reserve";
    }


}
