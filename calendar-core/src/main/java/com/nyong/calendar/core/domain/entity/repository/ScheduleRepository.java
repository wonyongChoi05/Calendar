package com.nyong.calendar.core.domain.entity.repository;

import com.nyong.calendar.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
