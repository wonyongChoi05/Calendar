package com.nyong.calendar.api.dto;

import com.nyong.calendar.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class NotificationDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime notifyAt;
    private final String title;
    private final Long writerId;
    private final ScheduleType scheduleType;

    @Override
    public ScheduleType scheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}
