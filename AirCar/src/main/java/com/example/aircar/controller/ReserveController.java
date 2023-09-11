package com.example.aircar.controller;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.domain.MemberSecurityDTO;
import com.example.aircar.domain.ReserveDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.entity.Files;
import com.example.aircar.entity.Member;
import com.example.aircar.entity.Reserve;
import com.example.aircar.repository.CarRepository;
import com.example.aircar.repository.FilesRepository;
import com.example.aircar.repository.MemberRepository;
import com.example.aircar.repository.ReserveRepository;
import com.example.aircar.service.CarService;
import com.example.aircar.service.FilesService;
import com.example.aircar.service.ReserveService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@Log4j2
@Valid
public class ReserveController {

    private CarRepository carRepository;
    private FilesRepository filesRepository;
    private FilesService filesService;
    private CarService carService;
    private ReserveService reserveService;
    private MemberRepository memberRepository;
    private ReserveRepository reserveRepository;

//    @GetMapping("/reserve")
//    public String reserve(){
//        return "/reserve/reserve";
//    }



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
    public String car_detail(Long carNum, Model model,String carImg){
//        Car car = carRepository.findByCarNum(carNum);
//        List<Files> files = filesRepository.findByFileNum(carNum);
        CarDTO carDTO = carService.getCarInfo(carNum);


        model.addAttribute("car" ,carDTO);
//        model.addAttribute("files" ,files);
//        model.addAttribute("carinfo",carService.getCarInfo());

        log.info(carService.getCarInfo());
        return "reserve/car_detail";
    }


    @GetMapping("/reserve_confirm")
    public String reserveConfirm(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model, ReserveDTO reserveDTO, HttpSession httpSession){
//        List<Reserve> reserve = reserveService.findUUIDByEmail(memberSecurityDTO.getEmail());
        List<ReserveDTO> reserveDTOList = reserveService.getReserveInfoByEmail(memberSecurityDTO.getEmail());
//        log.info("로그번호 : {} ",reserve);
        model.addAttribute("reserveList", reserveDTOList);
//        log.info("예약번호 :  {}" , reserve);


        return "/reserve/reserve_confirm";
    }


//    @GetMapping("/upload")
//    public String upload(){
//
//        return "/admin/upload";
//    }

    @GetMapping("/reserve")
    public String reserve(Model model){
//        List<Car> carList = carRepository.findAll();
//        model.addAttribute("car" , carList);
//        List<CarDTO> carDTOList = carService.getCarInfo();

//        model.addAttribute("carList",carService.getCarList());
        model.addAttribute("carInfo",carService.getCarInfo());

        return "reserve/reserve";
    }

    @GetMapping("/reserve_first")
    public String reserve2(Long carNum, Model model, @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO){
        Car car = carRepository.findByCarNum(carNum);
        model.addAttribute("car" ,car);
        model.addAttribute("carPay", carService.getCarInfo());
        model.addAttribute("email",memberSecurityDTO.getEmail());
        log.info("email : " + memberSecurityDTO.getEmail());

        return "reserve/reserve_first";
    }


}
