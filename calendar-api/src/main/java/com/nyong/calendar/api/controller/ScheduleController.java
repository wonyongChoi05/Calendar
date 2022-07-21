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

@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq,
                                           AuthUser authUser) {

        taskService.create(taskCreateReq, authUser);
        return ResponseEntity.ok().build();
    }
}
