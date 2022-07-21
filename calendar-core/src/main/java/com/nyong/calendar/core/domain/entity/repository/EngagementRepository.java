package com.nyong.calendar.core.domain.entity.repository;

import com.nyong.calendar.core.domain.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {

    List<Engagement> findAllByAttendeeId(Long id);
}
