package com.nyong.calendar.core.dto;

import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.util.Encryptor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateReq {

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;

    public User toEntity(Encryptor encryptor) {
        return User.builder()
                .name(name)
                .email(email)
                .password(encryptor.encrypt(password))
                .birthday(birthday)
                .build();
    }

}
