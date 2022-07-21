package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.api.dto.NotificationCreateReq;
import com.nyong.calendar.core.domain.entity.Schedule;
import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.domain.entity.repository.ScheduleRepository;
import com.nyong.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    @Transactional
    public void create(NotificationCreateReq req, AuthUser authUser) {
        final User writer = userService.findById(authUser.getId());
        req.getRepeatTimes()
                .forEach(notifyAt ->
                        scheduleRepository.save(Schedule.notification(req.getTitle(), notifyAt, writer)));
    }
}
