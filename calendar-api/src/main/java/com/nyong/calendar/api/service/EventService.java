package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.api.dto.EventCreateReq;
import com.nyong.calendar.core.domain.RequestStatus;
import com.nyong.calendar.core.domain.entity.Engagement;
import com.nyong.calendar.core.domain.entity.Schedule;
import com.nyong.calendar.core.domain.entity.User;
import com.nyong.calendar.core.domain.entity.repository.EngagementRepository;
import com.nyong.calendar.core.domain.entity.repository.ScheduleRepository;
import com.nyong.calendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
            throw new RuntimeException("이미 초대한 상대이거나, 중복된 시간대에 일정을 등록할 수 없습니다.");
        }

        Schedule eventSchedule = Schedule.event(
                eventCreateReq.getTitle(),
                eventCreateReq.getDescription(),
                eventCreateReq.getStartAt(),
                eventCreateReq.getEndAt(),
                userService.findById(authUser.getId())
        );

        scheduleRepository.save(eventSchedule);

        eventCreateReq.getAttendeeIds()
                .forEach(id -> {
                    Engagement engagement = Engagement.builder()
                            .schedule(eventSchedule)
                            .requestStatus(RequestStatus.REQUESTED)
                            .attendee(userService.findById(id))
                            .build();

                    engagementRepository.save(engagement);
                    emailService.sendEngagement(engagement);
                });
    }
}
