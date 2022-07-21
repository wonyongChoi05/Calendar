package com.nyong.calendar.api.util;

import com.nyong.calendar.api.dto.EventDto;
import com.nyong.calendar.api.dto.NotificationDto;
import com.nyong.calendar.api.dto.ScheduleDto;
import com.nyong.calendar.api.dto.TaskDto;
import com.nyong.calendar.core.domain.entity.Schedule;

public abstract class DtoConverter {

    public static ScheduleDto toForListDto(Schedule schedule) {
        switch (schedule.getScheduleType()) {
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .taskAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .scheduleId(schedule.getId())
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw new RuntimeException("bad request. not matched schedule type.");
        }
    }
}
