package com.nyong.calendar.core.dto;

import com.nyong.calendar.core.domain.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCreateReq {

    private final String name;
    private final String email;
    private final String password;
    private final LocalDateTime birthday;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .birthday(birthday)
                .build();
    }

}
