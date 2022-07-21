package com.nyong.calendar.core.domain.entity;

import com.nyong.calendar.core.domain.Event;
import com.nyong.calendar.core.domain.Notification;
import com.nyong.calendar.core.domain.ScheduleType;
import com.nyong.calendar.core.domain.Task;
import com.nyong.calendar.core.util.Period;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Schedule extends BaseEntity{

    private String title;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String description;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private User writer;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    @Builder
    public static Schedule event(String title, String description, LocalDateTime startAt, LocalDateTime endAt, User writer) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    @Builder
    public static Schedule task(String title, String description, LocalDateTime taskAt, User writer) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(taskAt)
                .writer(writer)
                .scheduleType(ScheduleType.TASK)
                .build();
    }

    @Builder
    public static Schedule notification(String title, LocalDateTime notifyAt, User writer) {
        return Schedule.builder()
                .title(title)
                .startAt(notifyAt)
                .writer(writer)
                .scheduleType(ScheduleType.NOTIFICATION)
                .build();
    }

    public Task toTask() {
        return new Task(this);
    }

    public Event toEvent() {
        return new Event(this);
    }

    public Notification toNotification() {
        return new Notification(this);
    }

    public boolean isOverlapped(LocalDate date) {
        return Period.of(getStartAt(), getEndAt()).isOverlapped(date);
    }

    public boolean isOverlapped(Period period) {
        return Period.of(getStartAt(), getEndAt()).isOverlapped(period);
    }
}
