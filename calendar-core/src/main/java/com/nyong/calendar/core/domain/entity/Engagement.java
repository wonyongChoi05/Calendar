package com.nyong.calendar.core.domain.entity;

import com.nyong.calendar.core.domain.Event;
import com.nyong.calendar.core.domain.RequestReplyType;
import com.nyong.calendar.core.domain.RequestStatus;
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
public class Engagement extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Event getEvent() {
        return schedule.toEvent();
    }

    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }

    public Engagement reply(RequestReplyType type) {
        switch (type) {
            case ACCEPT:
                this.requestStatus = RequestStatus.ACCEPTED;
                break;

            case REJECT:
                this.requestStatus = RequestStatus.REQUESTED;
                break;
        }

        return this;
    }
}
