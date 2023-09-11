package com.example.aircar.service;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.domain.ReserveDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.entity.Reserve;
import com.example.aircar.repository.CarRepository;
import com.example.aircar.repository.FilesRepository;
import com.example.aircar.repository.ReserveRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Log4j2
public class ReserveService {

    private CarRepository carRepository;


    private ReserveRepository reserveRepository;
    private FilesRepository filesRepository;
    public List<Car> getCarList() {
        return carRepository.findAll();
    }

//    public Reserve reserveInsert(ReserveDTO reserveDTO, UUID )

    public Reserve saveReserve(ReserveDTO reserveDTO, HttpSession session, RedirectAttributes rttr) {
        Reserve reserve = new Reserve();
//        reserve.setRno(reserveDTO.getRno());
        reserve.setCarName(reserveDTO.getCarName());
        reserve.setCarNum(reserveDTO.getCarNum());
        reserve.setNickname(reserveDTO.getNickname());
        reserve.setEmail(reserveDTO.getEmail());
        reserve.setRegDate(reserveDTO.getRegDate());
//        reserve.setReserveDate(reserve.getReserveDate());
        return reserveRepository.save(reserve);
    }

    public  List<ReserveDTO> getReserveInfoByEmail(String email){
        List<Reserve> reserveList = reserveRepository.findByEmail(email);

        List<ReserveDTO> reserveDTOSList = new ArrayList<>();
        for(int i = 0; i < reserveList.size(); i++) {
            ReserveDTO reserveDTO = new ReserveDTO();
            reserveDTO.setRno(reserveList.get(i).getRno());
            reserveDTO.setEmail(reserveList.get(i).getEmail());
            reserveDTO.setCarNum(reserveList.get(i).getCarNum());
            reserveDTO.setCarName(reserveList.get(i).getCarName());
            reserveDTO.setReserveDate(reserveList.get(i).getReserveDate());
            reserveDTO.setNickname(reserveList.get(i).getNickname());
            reserveDTO.setRegDate(reserveList.get(i).getRegDate());
            reserveDTO.setBrandImg(filesRepository.findByCarName(reserveList.get(i).getCarName()).getBrandImg());
            reserveDTO.setCarImg(filesRepository.findByCarName(reserveList.get(i).getCarName()).getCarImg());

            reserveDTOSList.add(reserveDTO);
        }
        return reserveDTOSList;
    }
}
