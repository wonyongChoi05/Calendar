package com.nyong.calendar.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SignUpReq {

    private final String name;
    private final String email;
    private final String password;
    private final LocalDateTime birthday;

}
