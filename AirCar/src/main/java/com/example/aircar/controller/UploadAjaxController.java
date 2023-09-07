package com.example.aircar.controller;

import com.example.aircar.entity.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@Log4j2
public class UploadAjaxController {

    @Value("${com.example.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles,String carName, String brandName) throws IOException {
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+ carName);
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ "+brandName);
        for (MultipartFile uploadFile : uploadFiles) {

            if(uploadFile.getContentType().startsWith("image")== false) {
                return;
            }

            String originalName = uploadFile.getOriginalFilename();

            String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);

            String folderPath = makeFolder();
log.info("!!!!! folderPath === " + folderPath);
            String uuid = UUID.randomUUID().toString();

            //String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            String saveName =  uploadPath + File.separator +fileName;

            Path savePath = Paths.get(saveName);
            log.info("!!!!! savePath === " + savePath);
            try {
                uploadFile.transferTo(savePath);
//
//                Files files = new Files();
//                files.setCarName(carName);
//                files.setCarImg(brandName);
//                files.setBrandImg(fileDTO.getBrandImg().get(i));
//                files.setBrandName(fileDTO.getBrandName().get(i));
//
//                filesRepository.save(files);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private  String makeFolder() {
//        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        String folderPath = str.replace("/", File.separator);
//
//        File uploadPathFolder = new File(uploadPath, folderPath);
//
//        if (uploadPathFolder.exists() ==false){
//            uploadPathFolder.mkdirs();
//        }
//        return folderPath;
//    }
private String makeFolder() {
    File uploadPathFolder = new File(uploadPath);

    if (!uploadPathFolder.exists()) {
        uploadPathFolder.mkdirs();
    }

    return "";
}
}
