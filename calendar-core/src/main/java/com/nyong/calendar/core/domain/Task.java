package com.nyong.calendar.core.domain;

import com.nyong.calendar.core.domain.entity.Schedule;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Task {

    private Schedule schedule;

}
