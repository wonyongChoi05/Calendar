package com.nyong.calendar.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다."),
    ALREADY_EXISTS_USER("이미 존재하는 계정입니다."),
    USER_NOT_FOUND("존재하는 회원이 없습니다."),
    VALIDATION_FAIL("값이 유효하지 않습니다."),
    BAD_REQUEST("잘못된 접근입니다."),
    EVENT_CREATE_OVERLAPPED_PERIOD("이벤트 기간이 중복되었습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
