package com.nyong.calendar.api.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final JavaMailSender javaMailSender;

    @GetMapping("/api/mail")
    @ResponseBody
    public void sendTestMail() {
        final MimeMessagePreparator preparator = message -> {
            final MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("rltgjqmduftlagl@gmail.com");
            helper.setSubject("제목이다~");
            helper.setText("내용이다~");
        };

        javaMailSender.send(preparator);
    }

    @GetMapping("/test/template")
    public String testTemplate(Model model) {
        final Map<String, Object> props = new HashMap<>();
        props.put("title", "제목입니다.");
        props.put("calendar", "sample@gmail.com");
        props.put("period", "언제부터 언제까지");
        props.put("attendees", List.of("test@gmail.com", "test@gmail.com", "test@gmail.com"));
        props.put("acceptUrl", "http://localhost:8080");
        props.put("rejectUrl", "http://localhost:8080");
        model.addAllAttributes(props);

        return "engagement-email";
    }
}
