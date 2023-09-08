package com.example.aircar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public static JavaMailSender mailSender() {
        JavaMailSenderImpl jms = new JavaMailSenderImpl();
        jms.setHost("smtp.gmail.com");//google smtp 서버 설정(고정)
        jms.setPort(587);//네이버는 465(고정) //메일 포트
        jms.setUsername("wlduswn12@gmail.com");
        jms.setPassword("bewrguwuhrnvohnl");

        //세부사항
        Properties prop = new Properties();
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.setProperty("mail.debug", "true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        jms.setJavaMailProperties(prop);

        return jms;
    }
}
