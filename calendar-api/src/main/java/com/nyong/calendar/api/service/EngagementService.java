package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.core.domain.RequestReplyType;
import com.nyong.calendar.core.domain.RequestStatus;
import com.nyong.calendar.core.domain.entity.repository.EngagementRepository;
import com.nyong.calendar.core.exception.CalendarException;
import com.nyong.calendar.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        // engagement 조회
        // 참석자가 auth user 와 같은지 비교
        // requested 상태인지 체크
        return engagementRepository.findById(engagementId)
                .filter(e -> e.getRequestStatus() == RequestStatus.REQUESTED)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getRequestStatus();
    }
}
