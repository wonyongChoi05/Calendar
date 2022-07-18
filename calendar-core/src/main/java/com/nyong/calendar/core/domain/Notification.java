package com.nyong.calendar.core.domain;

import com.nyong.calendar.core.domain.entity.Schedule;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Notification {

    private Schedule schedule;

}
