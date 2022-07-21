package com.nyong.calendar.api.dto;

import lombok.Data;

@Data
public class AuthUser {
    private Long id;

    private AuthUser(Long id) {
        this.id = id;
    }

    public static AuthUser toAuthUser(Long id) {
        return new AuthUser(id);
    }
}
