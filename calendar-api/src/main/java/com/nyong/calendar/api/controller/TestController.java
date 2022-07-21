package com.nyong.calendar.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final JavaMailSender javaMailSender;

    @GetMapping("/api/mail")
    public void sendTestMail() {
        final MimeMessagePreparator preparator = message -> {
            final MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("rltgjqmduftlagl@gmail.com");
            helper.setSubject("제목이다~");
            helper.setText("내용이다~");
        };

        javaMailSender.send(preparator);
    }
}
