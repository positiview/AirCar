package com.example.aircar.service;


import com.example.aircar.entity.Files;
import com.example.aircar.repository.FilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FilesService {

    private FilesRepository filesRepository;

    public Files getCarImgData(String carName) {
        return filesRepository.findByCarName(carName);
    }
}
