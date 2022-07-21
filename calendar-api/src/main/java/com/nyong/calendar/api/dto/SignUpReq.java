package com.nyong.calendar.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpReq {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @Size(min = 6, message = "6자리 이상 입력해주세요.")
    @NotBlank
    private String password;

    @NotNull
    private LocalDate birthday;

}