package com.nyong.calendar.api.service;

import com.nyong.calendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement engagement);
}
