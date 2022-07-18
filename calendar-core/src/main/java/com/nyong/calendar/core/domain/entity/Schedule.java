package com.nyong.calendar.core.domain.entity;

import com.nyong.calendar.core.domain.Event;
import com.nyong.calendar.core.domain.Notification;
import com.nyong.calendar.core.domain.ScheduleType;
import com.nyong.calendar.core.domain.Task;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    private String title;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private String description;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private User writer;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    private LocalDateTime createdAt = LocalDateTime.now();

    private static Schedule event(String title, String description, LocalDateTime startAt, LocalDateTime endAt, User writer) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    private static Schedule task(String title, String description, LocalDateTime taskAt, User writer) {
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(taskAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    private static Schedule notification(String title, LocalDateTime notifyAt, User writer) {
        return Schedule.builder()
                .title(title)
                .startAt(notifyAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
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
}
