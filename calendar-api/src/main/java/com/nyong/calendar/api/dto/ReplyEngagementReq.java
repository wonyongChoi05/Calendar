package com.nyong.calendar.api.dto;

import com.nyong.calendar.core.domain.RequestReplyType;
import lombok.Data;

@Data
public class ReplyEngagementReq {
    private final RequestReplyType type;

    public ReplyEngagementReq(RequestReplyType type) {
        this.type = type;
    }

    public RequestReplyType getType() {
        return type;
    }
}
