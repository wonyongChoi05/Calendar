package com.nyong.calendar.api.service;

import com.nyong.calendar.api.dto.EngagementEmailStuff;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
}
