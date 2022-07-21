package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.AuthUser;
import com.nyong.calendar.api.dto.ScheduleDto;
import com.nyong.calendar.api.util.DtoConverter;
import com.nyong.calendar.core.domain.entity.Engagement;
import com.nyong.calendar.core.domain.entity.Schedule;
import com.nyong.calendar.core.domain.entity.repository.EngagementRepository;
import com.nyong.calendar.core.domain.entity.repository.ScheduleRepository;
import com.nyong.calendar.core.util.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleQueryService {
    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;

    public List<ScheduleDto> getSchedulesByDay(LocalDate date, AuthUser authUser) {
        return Stream.concat(
                scheduleRepository
                        .findAllByWriter_Id(authUser.getId())
                        .stream()
                        .filter(schedule -> schedule.isOverlapped(date))
                        .map(DtoConverter::toForListDto),

                engagementRepository
                        .findAllByAttendeeId(authUser.getId())
                        .stream()
                        .filter(engagement -> engagement.isOverlapped(date))
                        .map(engagement -> DtoConverter.toForListDto(engagement.getSchedule()))
        ).collect(toList());
    }

    public List<ScheduleDto> getSchedulesByWeek(LocalDate startOfWeek, AuthUser authUser) {
        final Period period = Period.of(startOfWeek, startOfWeek.plusDays(6));
        return Stream.concat(
                scheduleRepository
                        .findAllByWriter_Id(authUser.getId())
                        .stream()
                        .filter(schedule -> schedule.isOverlapped(period))
                        .map(DtoConverter::toForListDto),
                engagementRepository
                        .findAllByAttendeeId(authUser.getId())
                        .stream()
                        .filter(engagement -> engagement.isOverlapped(period))
                        .map(engagement -> DtoConverter.toForListDto(engagement.getSchedule()))
        ).collect(toList());
    }

    public List<ScheduleDto> getSchedulesByMonth(YearMonth yearMonth, AuthUser authUser) {
        final Period period = Period.of(yearMonth.atDay(1), yearMonth.atEndOfMonth());
        return Stream.concat(
                scheduleRepository
                        .findAllByWriter_Id(authUser.getId())
                        .stream()
                        .filter(schedule -> schedule.isOverlapped(period))
                        .map(DtoConverter::toForListDto),
                engagementRepository
                        .findAllByAttendeeId(authUser.getId())
                        .stream()
                        .filter(engagement -> engagement.isOverlapped(period))
                        .map(engagement -> DtoConverter.toForListDto(engagement.getSchedule()))
        ).collect(toList());
    }
}
