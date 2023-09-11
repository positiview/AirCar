package com.example.aircar.controller;

import com.example.aircar.entity.Files;
import com.example.aircar.service.FilesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FilesController {

    private FilesService filesService;
    @GetMapping("/getCarImg")
    public Files getCarImgData(String carName){
        return filesService.getCarImgData(carName);
    }
}
