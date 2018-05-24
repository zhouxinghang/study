package com.zhouxinghang.study.guava.eventbus;

/**
 * Created by zhouxinghang on 2018/5/24.
 */
public class EventEntity {

    private Long eventId;

    private String message;

    public EventEntity(Long eventId, String message) {
        this.eventId = eventId;
        this.message = message;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
               "eventId=" + eventId +
               ", message='" + message + '\'' +
               '}';
    }
}
