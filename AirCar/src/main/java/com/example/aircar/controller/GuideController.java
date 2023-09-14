package com.example.aircar.controller;

import com.example.aircar.domain.CounselingDTO;
import com.example.aircar.domain.NoticesDTO;
import com.example.aircar.entity.Counseling;
import com.example.aircar.entity.Notices;
import com.example.aircar.repository.CounselingRepository;
import com.example.aircar.repository.NoticesRepository;
import com.example.aircar.service.NoticesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class GuideController {

    private final CounselingRepository counselingRepository;
    private final NoticesRepository noticesRepository;
    private final NoticesService noticesService;

//    @GetMapping("/crm")
//    public String crmForm(){
//        return "/guide/crm";
//    }

    @GetMapping("/crm")
    public String crm(Model model,
                      @RequestParam(defaultValue = "") String keyword,
                      @PageableDefault(size = 5, sort = "bno", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("noticesList", noticesService.getNoticesTitleList1(keyword, pageable));
        model.addAttribute("keyword", keyword);
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


//    @GetMapping("/notices")
//    public String notices(Model model){
//        List<Notices> noticesList = noticesRepository.findAll();
//        model.addAttribute("noticesList", noticesList);
//
//        return "/guide/notices";
//    }

    @GetMapping("/notices")
    public String notices(Model model,@RequestParam(name = "id",defaultValue = "") String dataId,
                          @RequestParam(defaultValue = "") String keyword,
                          @PageableDefault(size = 10, sort = "bno", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("noticesList", noticesService.getNoticesTitleList(keyword, pageable));
        model.addAttribute("keyword", keyword);
        model.addAttribute("dataId", dataId);
        return "/guide/notices";
    }


    @GetMapping("/getData")
    @ResponseBody
    public List<Notices> getData(Model model, @RequestParam(defaultValue = "") String keyword, Pageable pageable) {
        //model.addAttribute("noticesList", noticesService.getNoticesTitleList1(keyword,pageable));

        Page<Notices> noticeList = noticesService.getNoticesTitleList1(keyword,pageable);

        return noticeList.getContent();
    }
    @GetMapping("/noticesView")
    public String noticesView(Long bno, Model model){
        Notices notices = noticesRepository.findByBno(bno);

        model.addAttribute("notices", notices);

        return "/guide/notices_view";
    }



}
