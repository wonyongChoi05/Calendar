package com.nyong.calendar.api.controller.api;

import com.nyong.calendar.api.dto.*;
import com.nyong.calendar.api.service.*;
import com.nyong.calendar.core.domain.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequestMapping("/api/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;
    private final ScheduleQueryService scheduleQueryService;
    private final EngagementService engagementService;


    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskCreateReq taskCreateReq,
                                           AuthUser authUser) {
        taskService.create(taskCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventCreateReq eventCreateReq,
                                            AuthUser authUser) {
        eventService.create(eventCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> createNotification(
            @RequestBody NotificationCreateReq notificationCreateReq,
            AuthUser authUser) {
        notificationService.create(notificationCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/day")
    public List<ScheduleDto> getSchedulesByDay(
            AuthUser authUser,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return scheduleQueryService.getSchedulesByDay(date == null ? LocalDate.now() : date, authUser);
    }

    @GetMapping("/week")
    public List<ScheduleDto> getSchedulesByWeek(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek
    ) {
        return scheduleQueryService.getSchedulesByWeek(startOfWeek == null ? LocalDate.now() : startOfWeek, authUser);
    }

    @GetMapping("/month")
    public List<ScheduleDto> getSchedulesByMonth(
            AuthUser authUser,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM") String yearMonth
    ) {
        return scheduleQueryService.getSchedulesByMonth(yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth), authUser);
    }

    @PutMapping("/events/engagement/{engagementId}")
    public RequestStatus updateEngagement(
            @Valid @RequestBody ReplyEngagementReq replyEngagementReq,
            @PathVariable Long engagementId,
            AuthUser authUser) {
        return engagementService.update(authUser, engagementId, replyEngagementReq.getType());
    }
}