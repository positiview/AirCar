package com.example.aircar.controller;

import com.example.aircar.domain.NoticesDTO;
import com.example.aircar.entity.Notices;
import com.example.aircar.repository.NoticesRepository;
import com.example.aircar.service.NoticesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final NoticesRepository noticesRepository;
    private final NoticesService noticesService;


    @GetMapping("/main")
    public String main(){
        return "/admin/main";
    }

    @GetMapping("/member")
    public String member(){
        return "/admin/member";
    }

    @GetMapping("/notices")
    public String notices(Model model){
        List<Notices> noticesList = noticesRepository.findAll();
        model.addAttribute("noticesList", noticesList);

        return "admin/notices";
    }

//        @GetMapping("/notices")
//        public String list(Model model, String keyword,
//                       @PageableDefault(size = 5, sort = "bno",
//                               direction = Sort.Direction.DESC) Pageable pageable) {
//
//        model.addAttribute("list",
//                noticesService.getNoticesList(pageable));
//
//            return "admin/notices";
//    }

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
}


