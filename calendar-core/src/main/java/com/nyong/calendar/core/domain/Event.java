package com.nyong.calendar.core.domain;

import com.nyong.calendar.core.domain.entity.Schedule;
import com.nyong.calendar.core.util.Period;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Event {

    private Schedule schedule;

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return schedule.getStartAt().isBefore(endAt) && startAt.isBefore(schedule.getEndAt());
    }

    public String getTitle() {
        return this.schedule.getTitle();
    }

    public Period getPeriod() {
        return Period.of(this.schedule.getStartAt(), this.schedule.getEndAt());
    }
}
