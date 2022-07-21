package com.nyong.calendar.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginReq {

    private String email;

    @Size(min = 6, message = "6자리 이상 입력해주세요.")
    @NotBlank
    private String password;
}
