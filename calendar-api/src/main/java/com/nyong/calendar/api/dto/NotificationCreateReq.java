package com.nyong.calendar.api.dto;

import com.nyong.calendar.core.exception.CalendarException;
import com.nyong.calendar.core.exception.ErrorCode;
import com.nyong.calendar.core.util.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationCreateReq {

    private String title;
    private LocalDateTime notifyAt;
    private RepeatInfo repeatInfo;

    public List<LocalDateTime> getRepeatTimes() {
        if (repeatInfo == null) {
            return Collections.singletonList(notifyAt);
        }

        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                            long increment = (long) repeatInfo.interval.intervalValue * i;
                            switch (repeatInfo.interval.timeUnit) {
                                case DAY:
                                    return notifyAt.plusDays(increment);
                                case WEEK:
                                    return notifyAt.plusWeeks(increment);
                                case MONTH:
                                    return notifyAt.plusMonths(increment);
                                case YEAR:
                                    return notifyAt.plusYears(increment);
                                default:
                                    throw new CalendarException(ErrorCode.BAD_REQUEST);
                            }
                        }
                )
                .collect(toList());
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class RepeatInfo {
        private Interval interval;
        private int times;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Interval {
        private int intervalValue;
        private TimeUnit timeUnit;
    }
}
