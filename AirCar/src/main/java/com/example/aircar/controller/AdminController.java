package com.example.aircar.controller;

import com.example.aircar.domain.CounselingDTO;
import com.example.aircar.domain.NoticesDTO;
import com.example.aircar.entity.Counseling;
import com.example.aircar.entity.Notices;
import com.example.aircar.repository.CounselingRepository;
import com.example.aircar.repository.NoticesRepository;
import com.example.aircar.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Log4j2
public class AdminController {


    private final NoticesRepository noticesRepository;
    private final CounselingRepository counselingRepository;
    private final MailService mailService;


    @GetMapping("/main")
    public String main(){
        return "/admin/main";
    }

    @GetMapping("/member")
    public String member(){
        return "/admin/member";
    }



    @GetMapping("/pencil")
    public String pencil(){
        return "/admin/pencil";
    }

    @GetMapping("/register")
    public String register(){
        return "/admin/register";
    }

    @GetMapping("/reserve")
    public String reserve(){
        return "/admin/reserve";
    }

    @GetMapping("/event")
    public String event(){ return "/admin/event"; }

    @GetMapping("/notices")
    public String notices(Model model){
        List<Notices> noticesList = noticesRepository.findAll();
        model.addAttribute("noticesList", noticesList);

        return "admin/notices";
    }

    @GetMapping("/noticesRegister")
    public String noticesRegister(){
        return "/admin/notices_register";
    }



    @PostMapping("/noticesRegister")
    public String noticesRegister(NoticesDTO noticesDto) {
        Notices notices = new Notices();

        notices.setNotices_title(noticesDto.getNotices_title());
        notices.setNotices_category(noticesDto.getNotices_category());
        notices.setNotices_content(noticesDto.getNotices_content());
        notices.setRegDate(noticesDto.getReg_time());

        noticesRepository.save(notices);



//        // 첨부파일 저장
//        if (noticesDto.get() != null && noticesDto.getFileNames().size() > 0) {
//            for (int i = 0; i < noticesDto.getFileNames().size(); i++) {
//                BoardAttach boardAttach = new BoardAttach();
//                boardAttach.setFileName(boardDto.getFileNames().get(i));
//                boardAttach.setThumbnailName(boardDto.getThumbnailNames().get(i));
//                boardAttach.setBoard(board);
//
//                boardAttachRepository.save(boardAttach);
//            }
//        }

        return "redirect:/admin/notices";
    }

    @GetMapping("/noticesView")
    public String noticesView(Long bno, Model model){
        Notices notices = noticesRepository.findByBno(bno);

        model.addAttribute("notices", notices);

        return "/admin/notices_view";
    }

    @GetMapping("/noticesUpdate")
    public String noticesUpdate(Long bno, Model model) {
        Notices notices = noticesRepository.findByBno(bno);

        model.addAttribute("notices", notices);

        return "admin/notices_update";
    }

    @PostMapping("/noticesUpdate")
    public String noticeUpdate(NoticesDTO noticesDto) {
        Notices notices = new Notices();

        notices.setBno(noticesDto.getBno());
        notices.setNotices_title(noticesDto.getNotices_title());
        notices.setNotices_category(noticesDto.getNotices_category());
        notices.setNotices_content(noticesDto.getNotices_content());
        notices.setRegDate(noticesDto.getReg_time());

        noticesRepository.save(notices);

        return "redirect:/admin/noticesView?bno=" + notices.getBno();
    }

    @GetMapping("/noticesDelete")
    public String noticesDelete(Long bno) {
        noticesRepository.deleteById(bno);

        return "redirect:/admin/notices";
    }

    @PostMapping("/noticesDelete")
    public String delete(NoticesDTO noticesDto) {
        // 게시글을 DB에서 삭제(+reply +boardAttach)
        noticesRepository.deleteById(noticesDto.getBno());

        return "redirect:/admin/notices";
    }

    @GetMapping("/counseling")
    public String counseling(Model model){
        List<Counseling> counselingList = counselingRepository.findAll();
        model.addAttribute("counselingList", counselingList);

        return "admin/counseling";
    }


    @GetMapping("/counselingAnswer")
    public String counselingAnswer(Long bno, Model model){
        Counseling counseling = counselingRepository.findByBno(bno);


        model.addAttribute("counseling", counseling);

        return "/admin/counseling_answer";
    }

    @PostMapping("/answer")
    public String answer(CounselingDTO counselingDto) {
        log.info("bno======================================" + counselingDto.getBno());
        Counseling counseling = new Counseling();

        counseling.setBno(counselingDto.getBno());

        counseling.setCounseling_email(counselingDto.getCounseling_email());
        counseling.setCounseling_title(counselingDto.getCounseling_title());
        counseling.setCounseling_content(counselingDto.getCounseling_content());
        counseling.setCounseling_type(counselingDto.getCounseling_type());
        counseling.setCounseling_stage(counselingDto.getCounseling_stage());

        counseling.setAnswer(counselingDto.getAnswer());

        counselingRepository.save(counseling);

        mailService.CreateMail(counselingDto);

        return "redirect:/admin/counselingAnswer?bno=" + counseling.getBno();
    }



}


