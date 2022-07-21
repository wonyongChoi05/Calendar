package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.api.dto.TaskCreateReq;
import com.nyong.calendar.core.domain.entity.Schedule;
import com.nyong.calendar.core.domain.entity.repository.ScheduleRepository;
import com.nyong.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    public void create(TaskCreateReq taskCreateReq, AuthUser authUser) {
        Schedule task = Schedule.task(
                taskCreateReq.getTitle(),
                taskCreateReq.getDescription(),
                taskCreateReq.getTaskAt(),
                userService.findById(authUser.getId()));

        scheduleRepository.save(task);
    }
}
