package com.example.aircar.service;

import com.example.aircar.domain.CounselingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    private static final String senderEmail = "wlduswn12@gmail.com";

    public void CreateMail(CounselingDTO counselingDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(counselingDto.getCounseling_email());
        message.setFrom(senderEmail);
        message.setSubject("[문의 답변드립니다]"+counselingDto.getCounseling_title());
        message.setText(counselingDto.getAnswer());

        javaMailSender.send(message);
    }
}
