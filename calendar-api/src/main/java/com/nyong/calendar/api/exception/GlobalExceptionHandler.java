package com.nyong.calendar.api.exception;

import com.nyong.calendar.core.exception.CalendarException;
import com.nyong.calendar.core.exception.ErrorCode;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CalendarException.class)
    public ResponseEntity<Void> handle(CalendarException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(null, ErrorHttpStatusMapper.mapToStatus(errorCode));
    }

    @Data
    public static class ErrorResponse {
        private final ErrorCode errorCode;
        private final String errorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        final ErrorCode errorCode = ErrorCode.VALIDATION_FAIL;
        return new ResponseEntity<>(
                new ErrorResponse(
                        errorCode,
                        Optional.ofNullable(e.getBindingResult().getFieldError())
                                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                .orElse(errorCode.getMessage())
                ), ErrorHttpStatusMapper.mapToStatus(errorCode));
    }

}
