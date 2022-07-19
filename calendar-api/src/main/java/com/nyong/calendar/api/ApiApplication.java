package com.nyong.calendar.api;

import com.nyong.calendar.core.Simple;
import com.nyong.calendar.core.SimpleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityListeners;
import java.util.List;

@RestController
@SpringBootApplication
@EntityScan("com.nyong.calendar.core")
@EnableJpaRepositories("com.nyong.calendar.core")
@EntityListeners(AuditingEntityListener.class)
public class ApiApplication {

    private final SimpleRepository simpleRepository;

    public ApiApplication(SimpleRepository simpleRepository) {
        this.simpleRepository = simpleRepository;
    }

    @GetMapping("/findAll")
    public List<Simple> findAll() {
        return simpleRepository.findAll();
    }

    @PostMapping("/save")
    public Simple save() {
        Simple simple = new Simple("nyong");

        return simpleRepository.save(simple);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
