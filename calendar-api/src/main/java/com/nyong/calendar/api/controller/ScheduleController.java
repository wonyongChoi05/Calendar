package com.nyong.calendar.api.controller;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.api.dto.TaskCreateReq;
import com.nyong.calendar.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.nyong.calendar.api.service.LoginService.LOGIN_SESSION_KEY;

@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq, HttpSession session) {
        final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);

        if (userId != null) {
            throw new RuntimeException("로그인 후 이용할 수 있습니다.");
        }

        taskService.create(taskCreateReq, AuthUser.toAuthUser(userId));
        return ResponseEntity.ok().build();
    }
}
