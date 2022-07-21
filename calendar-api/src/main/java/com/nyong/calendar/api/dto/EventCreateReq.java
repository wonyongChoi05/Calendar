package com.nyong.calendar.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventCreateReq {

    private String title;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private List<Long> attendeeIds;
}
