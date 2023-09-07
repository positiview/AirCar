package com.example.aircar.controller;

import com.example.aircar.domain.CounselingDTO;
import com.example.aircar.entity.Counseling;
import com.example.aircar.repository.CounselingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class GuideController {

    private final CounselingRepository counselingRepository;

    @GetMapping("/crm")
    public String crmForm(){
        return "/guide/crm";
    }

    @GetMapping("/instructions")
    public String instructions(){
        return "/guide/instructions";
    }

    @GetMapping("/short_renting")
    public String shosrt(){
        return "/guide/short_renting";
    }

    @GetMapping("/long_personal")
    public String personal(){
        return "/guide/long_personal";
    }

    @GetMapping("/discount")
    public String discount(){
        return "/guide/discount";
    }

    @GetMapping("/electric")
    public String electric(){
        return "/guide/electric";
    }

    @GetMapping("/insurance")
    public String insurance(){
        return "/guide/insurance";
    }

    @GetMapping("/long_corporation")
    public String corporation(){
        return "/guide/long_corporation";
    }

    @GetMapping("/counseling")
    public String counseling(){
        return "/guide/counseling";
    }

    @PostMapping("/counselingRegister")
    public String counselingRegister(CounselingDTO counselingDto) {
        Counseling counseling = new Counseling();

        counseling.setCounseling_email(counselingDto.getCounseling_email());
        counseling.setCounseling_type(counselingDto.getCounseling_type());
        counseling.setCounseling_stage(counselingDto.getCounseling_stage());
        counseling.setCounseling_title(counselingDto.getCounseling_title());
        counseling.setCounseling_content(counselingDto.getCounseling_content());
        counseling.setRegDate(counselingDto.getReg_time());

        counseling.setAnswer(counselingDto.getAnswer());

        counselingRepository.save(counseling);



        return "redirect:/reserve";
    }


}