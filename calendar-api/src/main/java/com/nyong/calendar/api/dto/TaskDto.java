package com.nyong.calendar.api.dto;

import com.nyong.calendar.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TaskDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime taskAt;
    private final String title;
    private final String description;
    private final Long writerId;

    @Override
    public ScheduleType scheduleType() {
        return ScheduleType.TASK;
    }
}
