package com.nyong.calendar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityListeners;

@EntityScan("com.nyong.calendar.core")
@EnableJpaRepositories("com.nyong.calendar.core")
@ComponentScan("com.nyong.calendar.core")
@SpringBootApplication
@EntityListeners(AuditingEntityListener.class)
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
