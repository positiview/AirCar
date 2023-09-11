package com.example.aircar.service;

import com.example.aircar.domain.CarDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.entity.Files;
import com.example.aircar.repository.CarRepository;
import com.example.aircar.repository.FilesRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;
    private FilesRepository filesRepository;
    public Car saveCar(CarDTO carDTO) {
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setName(carDTO.getName());
        car.setFuel(carDTO.getFuel());
        car.setColor(carDTO.getColor());
        car.setCost(carDTO.getCost());
        car.setYear(carDTO.getYear());
        car.setOptions(carDTO.getOptions());
        car.setPeople(carDTO.getPeople());
        car.setDriverAge(carDTO.getDriverAge());
        car.setDriverCareer(carDTO.getDriverCareer());
        car.setDefect(carDTO.getDefect());
        car.setContent(carDTO.getContent());
        carRepository.save(car);
        return carRepository.save(car);
    }

    public List<String> getAllCarBrand(){
        return filesRepository.findDistinctByBrandName();
    }

    public List<Files> getAllCarModel(String brandName) {
        return filesRepository.findByBrandName(brandName);
    }



    public List<CarDTO> getCarInfo() {
        List<Car> carList = carRepository.findAll();
        List<Files> filesList = filesRepository.findAll();
        List<CarDTO> carDtoList = new ArrayList<>();

        if(carList != null && carList.size() > 0) {
            for(int i = 0; i < carList.size(); i++) {
                CarDTO carDTO = new CarDTO();
                carDTO.setCar_num(carList.get(i).getCarNum());
                carDTO.setKind(carList.get(i).getKind());
                carDTO.setColor(carList.get(i).getColor());
                carDTO.setBrand(carList.get(i).getBrand());
                carDTO.setName(carList.get(i).getName());
                carDTO.setCost(carList.get(i).getCost());
                carDTO.setYear(carList.get(i).getYear());
                carDTO.setOptions(carList.get(i).getOptions());
                carDTO.setFuel(carList.get(i).getFuel());
                carDTO.setPeople(carList.get(i).getPeople());
                carDTO.setArea(carList.get(i).getArea());
                carDTO.setDetailarea(carList.get(i).getDetailarea());
                carDTO.setDefect(carList.get(i).getDefect());
                carDTO.setContent(carList.get(i).getContent());
                carDTO.setRegDate(carList.get(i).getRegDate());
                carDTO.setUpdateDate(carList.get(i).getUpdateDate());
                carDTO.setDriverAge(carList.get(i).getDriverAge());
                carDTO.setDriverCareer(carList.get(i).getDriverCareer());

                if(filesList != null && filesList.size() > 0) {
                    for(int j = 0; j < filesList.size(); j++) {
                        if(filesList.get(j).getCarName().equals(carList.get(i).getName())) {
                            carDTO.setBrandImg(filesList.get(j).getBrandImg());
                            carDTO.setCarImg(filesList.get(j).getCarImg());
                        }
                    }
                }
                carDtoList.add(carDTO);
            }
        }
        return carDtoList;
    }

    public List<Car> getCarList() {
        return carRepository.findAll();
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


//    public String getCarImageByModel(String carImg) {
//        List<Files> filesList = filesRepository.findByCarName(carImg);
//        if (!filesList.isEmpty()) {
//            return filesList.get(0).getImageUrl();
//        } else {
//            return "";
//        }
//    }


    public CarDTO getCarInfo(Long carNum) {
        Car car = carRepository.findByCarNum(carNum);
        Files imgFiles = filesRepository.findByCarName(car.getName());
        CarDTO carDTO = new CarDTO();
        carDTO.setCar_num(car.getCarNum());
        carDTO.setKind(car.getKind());
        carDTO.setColor(car.getColor());
        carDTO.setBrand(car.getBrand());
        carDTO.setName(car.getName());
        carDTO.setCost(car.getCost());
        carDTO.setYear(car.getYear());
        carDTO.setOptions(car.getOptions());
        carDTO.setFuel(car.getFuel());
        carDTO.setPeople(car.getPeople());
        carDTO.setArea(car.getArea());
        carDTO.setDetailarea(car.getDetailarea());
        carDTO.setDefect(car.getDefect());
        carDTO.setContent(car.getContent());
        carDTO.setRegDate(car.getRegDate());
        carDTO.setUpdateDate(car.getUpdateDate());
        carDTO.setDriverAge(car.getDriverAge());
        carDTO.setDriverCareer(car.getDriverCareer());
        carDTO.setBrandImg(imgFiles.getBrandImg());
        carDTO.setCarImg(imgFiles.getCarImg());

        return carDTO;
    }

}

