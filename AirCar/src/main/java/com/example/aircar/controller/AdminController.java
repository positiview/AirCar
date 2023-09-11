package com.example.aircar.controller;

import com.example.aircar.domain.CounselingDTO;
import com.example.aircar.domain.MemberDTO;
import com.example.aircar.domain.NoticesDTO;
import com.example.aircar.entity.Car;
import com.example.aircar.entity.Counseling;
import com.example.aircar.entity.Member;
import com.example.aircar.entity.Notices;
import com.example.aircar.repository.*;
import com.example.aircar.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
@Log4j2
public class AdminController {


    private final NoticesRepository noticesRepository;
    private final CounselingRepository counselingRepository;
    private final MemberRepository memberRepository;
    private final MailService mailService;
    private FilesRepository filesRepository;
    private final CarRepository carRepository;
    private final NoticesService noticesService;
    private final CounselingService counselingService;
    private final MemberService memberService;
    private final CarService carService;



    @GetMapping("/main")
    public String main(){
        return "/admin/main";
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


//    @GetMapping("/notices")
//    public String notices(Model model){
//        List<Notices> noticesList = noticesRepository.findAll();
//        model.addAttribute("noticesList", noticesList);
//
//        return "admin/notices";
//    }



    @GetMapping("/notices")
    public String notices(Model model,
                       @RequestParam(defaultValue = "")String searchType,
                       @RequestParam(defaultValue = "")String keyword,
                       @PageableDefault(size = 5, sort = "bno",
                               direction = Sort.Direction.DESC)Pageable pageable) {

        if (searchType.equals("title")) {
            model.addAttribute("noticesList",
                    noticesService.getNoticesTitleList(keyword, pageable));
        } else if (searchType.equals("content")) {
            model.addAttribute("noticesList",
                    noticesService.getNoticesContentList(keyword, pageable));
        } else {
            model.addAttribute("noticesList",
                    noticesService.getNoticesList(pageable));
        }


        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

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
    public String counseling(Model model,
                             @RequestParam(defaultValue = "")String searchType,
                              @RequestParam(defaultValue = "")String keyword,
                              @PageableDefault(size = 5, sort = "bno",
                                  direction = Sort.Direction.DESC)Pageable pageable){

        if (searchType.equals("title")) {
            model.addAttribute("counselingList",
                    counselingService.getCounselingTitleList(keyword, pageable));
        } else if (searchType.equals("content")) {
            model.addAttribute("counselingList",
                    counselingService.getCounselingTitleList(keyword, pageable));
        } else {
            model.addAttribute("counselingList",
                    counselingService.getCounselingList(pageable));
        }


        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "admin/counseling";
    }




    @GetMapping("/counselingAnswer")
    public String counselingAnswer(Long bno, Model model){
        Counseling counseling = counselingRepository.findByBno(bno);


        model.addAttribute("counseling", counseling);

        return "admin/counseling_answer";
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

    @GetMapping("/member")
    public String member(Model model,
                         @RequestParam(defaultValue = "")String searchType,
                         @RequestParam(defaultValue = "")String keyword,
                         @PageableDefault(size = 5, sort = "mno",
                                 direction = Sort.Direction.DESC)Pageable pageable){

        if (searchType.equals("nickname")) {
            model.addAttribute("memberList",
                    memberService.getnicknameList(keyword, pageable));
        } else if (searchType.equals("email")) {
            model.addAttribute("memberList",
                    memberService.getemailList(keyword, pageable));
        } else {
            model.addAttribute("memberList",
                    memberService.getMemberList(pageable));
        }

        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "admin/member";
    }



    @GetMapping("/memberView")
    public String memberView(Long mno, Model model){
        Member member = memberRepository.findByMno(mno);


        model.addAttribute("member", member);

        return "admin/member_view";
    }

    @GetMapping("/memberUpdate")
    public String memberUpdate(Long mno, Model model) {
        Member member = memberRepository.findByMno(mno);

        model.addAttribute("member", member);

        return "admin/member_update";
    }

    @PostMapping("/memberUpdate")
    public String memberUpdate(MemberDTO memberDto) {
        Member member = new Member();

        member.setMno(memberDto.getMno());
        member.setPassword(memberDto.getPassword());
        member.setName((memberDto.getName()));
        member.setEmail(memberDto.getEmail());
        member.setSocial(member.isSocial());
        member.setClientName(member.getClientName());
        member.setNickname(memberDto.getNickname());
        member.setRole(memberDto.getRole());



        memberRepository.save(member);

        return "redirect:/admin/memberView?mno=" + member.getMno();
    }

    @GetMapping("/upload")
    public String upload(){

        return "/admin/upload";
    }

    @GetMapping("/car")
    public String car(Model model,
                      @RequestParam(defaultValue = "")String searchType,
                      @RequestParam(defaultValue = "")String keyword,
                      @PageableDefault(size = 5, sort = "carNum",
                              direction = Sort.Direction.DESC)Pageable pageable) {

        if (searchType.equals("title")) {
            model.addAttribute("carList",
                    carService.getbrandList(keyword, pageable));
        } else if (searchType.equals("content")) {
            model.addAttribute("carList",
                    carService.getnameList(keyword, pageable));
        } else {
            model.addAttribute("carList",
                    carService.getcarList1(pageable));
        }


        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "admin/car";
    }

    @GetMapping("/carView")
    public String carView(Long carNum, Model model){
        Car car = carRepository.findByCarNum(carNum);


        model.addAttribute("car", car);

        return "admin/car_view";
    }



}


