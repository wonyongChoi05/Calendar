package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.api.dto.EngagementEmailStuff;
import com.nyong.calendar.api.dto.EventCreateReq;
import com.nyong.calendar.core.domain.RequestStatus;
import com.nyong.calendar.core.domain.entity.Engagement;
import com.nyong.calendar.core.domain.entity.Schedule;
import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.domain.entity.repository.EngagementRepository;
import com.nyong.calendar.core.domain.entity.repository.ScheduleRepository;
import com.nyong.calendar.core.exception.CalendarException;
import com.nyong.calendar.core.exception.ErrorCode;
import com.nyong.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EmailService emailService;
    private final EngagementRepository engagementRepository;
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(EventCreateReq eventCreateReq, AuthUser authUser) {
        // 1. 이벤트 참여자의 다른 이벤트와 중복이 되면 안됨
        // Ex) 1시~2시 회의가 있는데, 그 시간대에 추가로 다른 회의를 등록할 수 없음
        // TODO 이메일 발송

        // TODO findAll() 개선 -> 실무에서 findAll() 안쓰고 쿼리문같은 걸로 녹여냄
        final List<Engagement> engagements = engagementRepository.findAll();

        // 1
        if (engagements.stream()
                .anyMatch(e -> eventCreateReq.getAttendeeIds().contains(e.getAttendee().getId())
                        && e.getRequestStatus() == RequestStatus.ACCEPTED
                        && e.getEvent().isOverlapped(eventCreateReq.getStartAt(), eventCreateReq.getEndAt()))) {
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }

        Schedule eventSchedule = Schedule.event(
                eventCreateReq.getTitle(),
                eventCreateReq.getDescription(),
                eventCreateReq.getStartAt(),
                eventCreateReq.getEndAt(),
                userService.findById(authUser.getId())
        );

        scheduleRepository.save(eventSchedule);

        List<User> attendees =
                eventCreateReq.getAttendeeIds().stream()
                        .map(userService::findById)
                        .collect(Collectors.toList());

        attendees.forEach(attendee -> {
                    Engagement engagement = Engagement.builder()
                            .schedule(eventSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(attendee)
                            .build();

                    engagementRepository.save(engagement);

                    emailService.sendEngagement(EngagementEmailStuff.builder()
                            .title(engagement.getEvent().getTitle())
                            .toEmail(engagement.getAttendee().getEmail())
                            .attendeeEmails(attendees.stream()
                                    .map(User::getEmail)
                                    .collect(Collectors.toList()))
                            .period(engagement.getEvent().getPeriod())
                            .build());
                });
    }
}
