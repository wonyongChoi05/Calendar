package com.nyong.calendar.core.domain.entity;

import com.nyong.calendar.core.domain.Event;
import com.nyong.calendar.core.domain.RequestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Engagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private User attendee;

    private LocalDateTime createdAt;

    private RequestStatus requestStatus;

    public Engagement(Long id, User attendee, LocalDateTime createdAt, RequestStatus requestStatus) {
        this.id = id;
        this.attendee = attendee;
        this.createdAt = createdAt;
        this.requestStatus = requestStatus;
    }
}
