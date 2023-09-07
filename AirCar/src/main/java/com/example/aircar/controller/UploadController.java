package com.example.aircar.controller;

import com.example.aircar.domain.FileDTO;
import com.example.aircar.entity.Files;
import com.example.aircar.repository.FilesRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class UploadController {

    private  FilesRepository filesRepository;

    @PostMapping("/saveFile")
    public String saveFile(FileDTO fileDTO) {
        if (fileDTO.getCarImg()!= null && fileDTO.getCarImg().size() > 0 ){
            for (int i =0; i<fileDTO.getCarImg().size(); i++) {
                Files files = new Files();
                files.setCarName(fileDTO.getCarName().get(i));
                files.setCarImg(fileDTO.getCarImg().get(i));
                files.setBrandImg(fileDTO.getBrandImg().get(i));
                files.setBrandName(fileDTO.getBrandName().get(i));

                filesRepository.save(files);

            }

        }
        return "redirect:/reserve";
    }



}
